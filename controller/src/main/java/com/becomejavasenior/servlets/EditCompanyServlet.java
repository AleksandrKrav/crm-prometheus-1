package com.becomejavasenior.servlets;

import com.becomejavasenior.*;
import com.becomejavasenior.Currency;
import com.becomejavasenior.config.ApplicationContextConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Artem Kozlenko <ajet5911@gmail.com> on 09.01.2016.
 */
@WebServlet("/editCompany")
@MultipartConfig
public class EditCompanyServlet extends HttpServlet {

    private TaskService taskService;
    private CompanyService companyService;
    private DealService dealService;
    private Company company;

    @Override
    public void init() throws ServletException {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationContextConfig.class);
        companyService = context.getBean(CompanyService.class);
        dealService = context.getBean(DealService.class);
        taskService = context.getBean(TaskService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));

        try {

            company = companyService.getById(id);
            req.setAttribute("company", company);

            List<User> users = companyService.getAllUsers();
            req.setAttribute("owners", users);

            List<PhoneType> phoneTypes = companyService.getAllPhoneType();
            req.setAttribute("phoneTypes", phoneTypes);

            List<Contact> contacts = companyService.getAllContacts();
            req.setAttribute("contacts", contacts);

            List<Position> positions = companyService.getAllPositions();
            req.setAttribute("positions", positions);

            List<TaskType> taskTypes = taskService.getAllTaskType();
            req.setAttribute("taskTypes", taskTypes);

            List<Deal> deals = dealService.getDealsByCompanyId(company.getId());
            req.setAttribute("deals", deals);



            int sumDeals = 0;
            for (Deal deal : deals){
                sumDeals += deal.getBudget().intValue();
            }
            Map<String, String> mapDeals = new HashMap<String, String>();
            if (deals.size() > 0){
                mapDeals.put("amount", String.valueOf(deals.size()));
                mapDeals.put("currency", deals.iterator().next().getCurrency().getName());
                mapDeals.put("sum", String.valueOf(sumDeals));
            }
            req.setAttribute("mapDeals", mapDeals);

            List<DealStatus> dealStatuses = dealService.getAllStatuses();
            req.setAttribute("dealStatuses", dealStatuses);

            req.getRequestDispatcher("pages/editCompany.jsp").forward(req, resp);

        }  catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getParameter("company-delete") != null){
            try {
                deleteCompany(req);
            } catch (ServiceException e) {
                e.printStackTrace();
            }
            resp.sendRedirect("/list_of_companies");
        }else {
            if (req.getParameter("edit") != null){
                if (req.getParameter("company-name").equals("") || req.getParameter("company-owner").equals("")||
                        req.getParameter("company-address").equals("")||req.getParameter("company-web").equals("")||
                        req.getParameter("company-email").equals("")||req.getParameter("company-phone-number").equals("")){
                    return;
                }
                try {
                    updateCompany(req, resp);
                } catch (ServiceException e) {
                    e.printStackTrace();
                }
            }
            if (req.getParameter("task-submit") != null){
                if (req.getParameter("task-description").equals("") || req.getParameter("task-date").equals("")||
                        req.getParameter("task-period-time").equals("")||req.getParameter("task-owner").equals("")||
                        req.getParameter("task-type").equals("")){
                    return;
                }
                try {
                    createTask(req);
                } catch (ServiceException e) {
                    e.printStackTrace();
                }
            }
            if (req.getParameter("contact-fix-submit") != null){
                joinContact(req);
            }
            if (req.getParameter("contact-submit") != null){
                createContact(req);
            }
            if (req.getParameter("deal-submit") != null){
                if (req.getParameter("deal-description").equals("") || req.getParameter("deal-budget").equals("")||
                        req.getParameter("deal-status").equals("")){
                    return;
                }
                try {
                    createDeal(req);
                } catch (ServiceException e) {
                    e.printStackTrace();
                }
            }

            resp.sendRedirect("editCompany?id=" + company.getId());
        }

    }

    private void updateCompany(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ServiceException {

        company.setName(req.getParameter("company-name"));
        company.setAddress(req.getParameter("company-address"));
        company.setWeb(req.getParameter("company-web"));
        company.setEmail(req.getParameter("company-email"));

        User user = company.getOwner();
        user.setId(Integer.parseInt(req.getParameter("company-owner")));
        company.setOwner(user);

        Phone phone = company.getPhones().iterator().next();
        phone.setValue(req.getParameter("company-phone-number"));
        PhoneType phoneType = new PhoneType();
        phoneType.setId(Integer.parseInt(req.getParameter("company-phone-type")));
        phone.setPhoneType(phoneType);
        Set<Phone> phoneSet = new HashSet<Phone>();
        phoneSet.add(phone);
        company.setPhones(phoneSet);

        String company_comment = req.getParameter("company-comment");
        if (!company_comment.equals("") || !company_comment.isEmpty()) {
            Comment comment = company.getComments().iterator().next();
            comment.setDateOfCreate(new Date());
            comment.setComment(company_comment);
            Set<Comment> comments = new HashSet<Comment>();
            comments.add(comment);
            company.setComments(comments);
        }

        // Create path components to save the file
        final Part filePart = req.getPart("files");
        final String fileName = getFileName(filePart);

        OutputStream out = null;
        InputStream filecontent = null;
        Set<com.becomejavasenior.File> files = new HashSet<com.becomejavasenior.File>();

        try {
            com.becomejavasenior.File file = company.getFiles().iterator().next();
            file.setDate(new Date());
            file.setName(fileName);

            filecontent = filePart.getInputStream();

            int read = 0;
            final byte[] bytes = new byte[1024];


            while ((read = filecontent.read(bytes)) != -1) {
                file.setData(bytes);
            }
            files.add(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            if (filecontent != null) {
                filecontent.close();
            }
        }

        company.setFiles(files);

        companyService.update(company);

    }

    private void deleteCompany(HttpServletRequest req) throws ServletException, IOException, ServiceException {

        companyService.delete(company);

    }

    private String getFileName(Part part) {
        final String partHeader = part.getHeader("content-disposition");
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

    private void createTask(HttpServletRequest req) throws ServletException, IOException, ServiceException {

        Task task = new Task();
        task.setDescription(req.getParameter("task-description"));

        task.setDateOfCreate(new Date());

        String stringDate = req.getParameter("task-date");
        String stringTime = req.getParameter("task-period-time");
        if (stringTime.equals("allDay")){
            stringTime = "23:59";
        }
        Date finishDate = null;
        try {
            finishDate = new SimpleDateFormat("MM/dd/yyyy hh:mm").parse(stringDate + " " + stringTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        task.setFinishDate(finishDate);

        task.setCompany(company);
        User user = new User();
        user.setId(Integer.parseInt(req.getParameter("task-owner")));
        task.setOwner(user);

        TaskType taskType = new TaskType();
        taskType.setId(Integer.parseInt(req.getParameter("task-type")));
        task.setType(taskType);

        TaskStatus taskStatus = new TaskStatus();
        taskStatus.setId(1);
        task.setStatus(taskStatus);

        taskService.add(task);

    }

    private void joinContact(HttpServletRequest req) throws ServletException, IOException{
    }

    private void createContact(HttpServletRequest req) throws ServletException, IOException{
    }

    private void createDeal(HttpServletRequest req) throws ServletException, IOException, ServiceException {

        Deal deal = new Deal();

        deal.setName(req.getParameter("deal-description"));

        deal.setBudget(new BigDecimal(req.getParameter("deal-budget")));

        DealStatus dealStatus = new DealStatus();
        dealStatus.setId(Integer.parseInt(req.getParameter("deal-status")));
        deal.setStatus(dealStatus);

        Currency currency = new Currency();
        currency.setId(1);
        deal.setCurrency(currency);

        deal.setOwner(company.getOwner());

        Date date = new Date();
        deal.setDate(new java.sql.Date(date.getTime()));

        deal.setCompany(company);

        dealService.add(deal);
    }

}
