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
import java.io.*;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@WebServlet("/add_deal")
@MultipartConfig
public class AddDealServlet extends HttpServlet {

    private UserService userService;
    private ContactDao contactDao;
    private CompanyService companyService;
    private TaskService taskService;
    private DealService dealService;
    private Currency currency;
    private Deal deal;

    @Override
    public void init() throws ServletException {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationContextConfig.class);
        userService = context.getBean(UserService.class);
        companyService = context.getBean(CompanyService.class);
        dealService = context.getBean(DealService.class);
        taskService = context.getBean(TaskService.class);
        contactDao = context.getBean(ContactDao.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users;
        List<DealStatus> statuses;
        List<Contact> contacts;
        List<Company> companies;
        List<PhoneType> phoneTypes ;
        List<Position> positions;

        try {
            users = userService.getAll();
            req.setAttribute("owners", users);

            statuses = dealService.getAllStatuses();
            req.setAttribute("statuses", statuses);

            currency = dealService.readCurrency(1);
            req.setAttribute("currency", currency);

            companies = companyService.getAll();
            req.setAttribute("companies", companies);

            phoneTypes = companyService.getAllPhoneType();
            req.setAttribute("phoneTypes", phoneTypes);

            contacts = contactDao.getAllAboveContact();
            req.setAttribute("contacts", contacts);

            positions = companyService.getAllPositions();
            req.setAttribute("positions", positions);

            List<Task> tasks = new LinkedList<Task>();
            List<TaskType> taskTypes = taskService.getAllTaskType();
            req.setAttribute("taskTypes", taskTypes);

            req.getRequestDispatcher("pages/addDeal.jsp").forward(req, resp);
        }  catch (ServiceException e) {
            //
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("deal-add") != null) {
            try {
                createDeal(req);
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        }
        if (req.getParameter("company-add-button") != null) {
            try {
                createCompany(req);
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        }

        if (req.getParameter("contact-add") != null){
            createContact(req);
        }

        if (req.getParameter("task-submit") != null){
            try {
                createTask(req);
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        }

        doGet(req, resp);
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

    private void createContact(HttpServletRequest req) throws ServletException, IOException{

        Contact contact = new Contact();
        contact.setName(req.getParameter("contact_name"));
        User user = new User();
        user.setId(Integer.parseInt(req.getParameter("contact-owner")));
        contact.setOwner(user);
        Company company = new Company();
        company.setId(Integer.parseInt(req.getParameter("contact_company")));
        contact.setCompany(company);
        Position pos = new Position();
        pos.setId(Integer.parseInt(req.getParameter("contact_position")));
        contact.setPosition(pos);
        PhoneType type = new PhoneType();
        type.setId(Integer.parseInt(req.getParameter("contact_phone-type")));
        Phone phone = new Phone();
        phone.setPhoneType(type);
        phone.setValue(req.getParameter("contact_phone-number"));
        Set<Phone> phones = new LinkedHashSet<>();
        phones.add(phone);
        contact.setPhones(phones);
        contact.setEmail(req.getParameter("contact_email"));
        contact.setSkype(req.getParameter("contact_skype"));

        contactDao.createContact(contact);

    }

    private void createCompany(HttpServletRequest req) throws ServletException, IOException, ServiceException {

        Company company = new Company();
        company.setName(req.getParameter("company_name"));
        User user = new User();
        user.setId(Integer.parseInt(req.getParameter("company-owner")));
        company.setOwner(user);
        Phone ph = new Phone();
        ph.setValue(req.getParameter("company-phone"));
        PhoneType phoneType = new PhoneType();
        phoneType.setId(1);
        ph.setPhoneType(phoneType);
        Set<Phone> phones = new LinkedHashSet<>();
        phones.add(ph);
        company.setPhones(phones);
        company.setEmail(req.getParameter("company-email"));
        company.setWeb(req.getParameter("company-web"));
        company.setAddress(req.getParameter("company-address"));

        companyService.add(company);

    }

    private void createDeal(HttpServletRequest req) throws ServletException, IOException, ServiceException {
        Deal deal = new Deal();
        deal.setDate(new Date());
        deal.setName(req.getParameter("deal-name"));

        String tagName = req.getParameter("deal-tag");
        if (tagName.startsWith("#") && tagName.length() > 1) {
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
        deal.setBudget(BigDecimal.valueOf(Double.parseDouble(req.getParameter("deal-budget"))));

        currency = new Currency();
        currency.setId(Integer.parseInt(req.getParameter("deal-currency")));
        deal.setCurrency(currency);

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
        String dealCompany = req.getParameter("company-name");
        if (!dealCompany.equals("") || !dealCompany.isEmpty()) {
            Company company = new Company();
            company.setId(Integer.parseInt(dealCompany));
            deal.setCompany(company);
        }
        String dealContact = req.getParameter("deal-contact");
        if (!dealContact.equals("") || !dealContact.isEmpty()){
            Contact contact = new Contact();
            contact.setId(Integer.parseInt(dealContact));
            List<Contact> contacts = new LinkedList<>();
            contacts.add(contact);
            deal.setContacts(contacts);
        }
        dealService.add(deal);
    }

    /**
     * Extracts file name from HTTP header content-disposition
     */
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
}
