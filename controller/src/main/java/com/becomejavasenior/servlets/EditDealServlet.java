package com.becomejavasenior.servlets;

import com.becomejavasenior.*;
import com.becomejavasenior.Currency;
import com.becomejavasenior.config.ApplicationContextConfig;
import com.becomejavasenior.daoContact.ContactDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@WebServlet("/edit_deal")
@MultipartConfig
public class EditDealServlet extends HttpServlet {

    private CompanyService companyService;
    private DealService dealService;
    private TaskService taskService;
    private ContactDao contactDao;
    private Deal deal;

    @Override
    public void init() throws ServletException {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationContextConfig.class);
        companyService = context.getBean(CompanyService.class);
        dealService = context.getBean(DealService.class);
        taskService = context.getBean(TaskService.class);
        contactDao = context.getBean(ContactDao.class);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("deal-delete") != null){
            try {
                deleteDeal(req);
            } catch (ServiceException e) {
                e.printStackTrace();
            }
            resp.sendRedirect("/list_of_deals");
        }else {
            if (req.getParameter("deal-edit") != null){
                if (req.getParameter("deal-name").equals("") || req.getParameter("deal-tag").equals("")||
                        req.getParameter("deal-owner").equals("")||req.getParameter("deal-budget").equals("")||
                        req.getParameter("deal-status").equals("")){
                    return;
                }
                try {
                    updateDeal(req, resp);
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
            if (req.getParameter("contact-add") != null){
                try {
                    createContact(req);
                } catch (ServiceException e) {
                    e.printStackTrace();
                }
            }
            resp.sendRedirect("edit_deal?id=" + deal.getId());
        }
    }

    private void createContact(HttpServletRequest req) throws ServiceException {
        Contact contact = new Contact();
        System.out.println(req.getParameter("contact_name"));
        contact.setName(req.getParameter("contact_name"));
        User user = new User();
        System.out.println(req.getParameter("contact-owner"));
        user.setId(Integer.parseInt(req.getParameter("contact-owner")));
        contact.setOwner(user);
        System.out.println(req.getParameter("contact_company"));
        Company company = new Company();
        company.setId(Integer.parseInt(req.getParameter("contact_company")));
        contact.setCompany(company);
        System.out.println(req.getParameter("contact_position"));
        Position pos = new Position();
        pos.setId(Integer.parseInt(req.getParameter("contact_position")));
        contact.setPosition(pos);
        PhoneType type = new PhoneType();
        System.out.println(req.getParameter("contact_phone-type"));
        type.setId(Integer.parseInt(req.getParameter("contact_phone-type")));
        Phone phone = new Phone();
        phone.setPhoneType(type);
        System.out.println(req.getParameter("contact_phone-number"));
        phone.setValue(req.getParameter("contact_phone-number"));
        Set<Phone> phones = new LinkedHashSet<>();
        phones.add(phone);
        contact.setPhones(phones);
        System.out.println(req.getParameter("contact_email"));
        contact.setEmail(req.getParameter("contact_email"));
        System.out.println(req.getParameter("contact_skype"));
        contact.setSkype(req.getParameter("contact_skype"));

        contact.setId(contactDao.createContact(contact));
        dealService.addContact(deal, contact);

    }

    private void createTask(HttpServletRequest req) throws ServiceException {
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

        task.setDeal(deal);
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

    private void updateDeal(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, ServiceException {
        deal.setName(req.getParameter("deal-name"));
        deal.setBudget(BigDecimal.valueOf(Double.parseDouble(req.getParameter("deal-budget"))));

        String tagName = req.getParameter("deal-tag");
//        if (tagName.startsWith("#") && tagName.length() > 1) {
        if (!tagName.equals("") && tagName.length() > 1) {
            Tag tag = new Tag();
            tag.setName(tagName);
            Set<Tag> tags = new HashSet<>();
            tags.add(tag);
            deal.setTags(tags);
        }

        String dealOwner = req.getParameter("deal-owner");
        if (!dealOwner.equals("") || !dealOwner.isEmpty()) {
            User user = new User();
            user.setId(Integer.parseInt(dealOwner));
            deal.setOwner(user);
        }

        DealStatus status = new DealStatus();
        status.setId(Integer.parseInt(req.getParameter("deal-status")));
        deal.setStatus(status);

        String dealComment = req.getParameter("deal-comment");
        if (!dealComment.equals("") || !dealComment.isEmpty()) {
            Comment comment = new Comment();
            comment.setDateOfCreate(new Date());
            comment.setComment(dealComment);
            Set<Comment> comments = new HashSet<>();
            comments.add(comment);
            deal.setComments(comments);
        }

        com.becomejavasenior.File file;
        Set<com.becomejavasenior.File> files = new HashSet<>();

        for (Part part : req.getParts()) {
            String fileName = getFileName(part);
            if (fileName != null) {
                System.out.println(fileName);
                file = new com.becomejavasenior.File();
                file.setDate(new Date());
                file.setName(fileName);
                try (InputStream in = part.getInputStream();
                ) {
                    int read = 0;
                    final byte[] bytes = new byte[(int) part.getSize()];
                    while ((read = in.read(bytes)) != -1) {
                        file.setData(bytes);
                    }
                }
                files.add(file);
            }
        }

        deal.setFiles(files);
        dealService.update(deal);

    }

    private String getFileName(final Part part) {
//        final String partHeader = part.getHeader("content-disposition");
//        LOGGER.log(Level.INFO, "Part Header = {0}", partHeader);
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

    private void deleteDeal(HttpServletRequest req) throws ServiceException {
        dealService.delete(deal);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        try {

            deal = dealService.getById(id);
            deal.setId(id);
            req.setAttribute("deal", deal);

            Company company = deal.getCompany();
            req.setAttribute("company", company);

            List<User> users = companyService.getAllUsers();
            req.setAttribute("owners", users);

            Currency cur = dealService.readCurrency(1);
            req.setAttribute("currency", cur);

            List<DealStatus> statuses = dealService.getAllStatuses();
            req.setAttribute("statuses", statuses);

            List<TaskType> taskTypes = taskService.getAllTaskType();
            req.setAttribute("taskTypes", taskTypes);

            List<Contact> contacts = deal.getContacts();
            req.setAttribute("contacts", contacts);

            List<Company> companies = companyService.getAll();
            req.setAttribute("companies",companies);

            List<Position> positions = companyService.getAllPositions();
            req.setAttribute("positions", positions);

            List<PhoneType> phoneTypes = companyService.getAllPhoneType();
            req.setAttribute("phoneTypes", phoneTypes);

            req.getRequestDispatcher("pages/editDeal.jsp").forward(req, resp);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}
