package com.becomejavasenior.servlets;

import com.becomejavasenior.*;
import com.becomejavasenior.config.ApplicationContextConfig;
import com.becomejavasenior.daoContact.ContactDao;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * Created by Shevchenko on 19.12.2015.
 */
public class InitAddContactServlet extends HttpServlet{
    private static final Logger log = Logger.getLogger(InitAddContactServlet.class);
    private UserService userService;
    private ContactDao contactDao;
    private TaskService taskService;
    private DealService dealService;
    private CompanyService companyService;
    private List<User> listUser;
    private Set<PhoneType> setPhoneType;
    private List<TaskType> listTaskType;
    private List<DealStatus> listDealStatus;
    private List<Company> listCompany;
    private Set<Position> setPosition;
    private List<Currency> listCurrency;

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
        //get connection, userDAO, contactDAO, taskDAO, dealDAO
        try {
            listUser = userService.getAll();
            listTaskType = taskService.getAllTaskType();
            listDealStatus = dealService.getAllStatuses();
            listCurrency = dealService.getAllCurrencies();
            listCompany = companyService.getAll();

            setPosition = contactDao.getAllPosition();
            setPhoneType = contactDao.getAllPhoneType();
        }  catch (ServiceException e) {
            e.printStackTrace();
        }


        req.setAttribute("owners", listUser);
        req.setAttribute("setPosition", setPosition);
        req.setAttribute("phoneTypes", setPhoneType);
        req.setAttribute("listTaskType", listTaskType);
        req.setAttribute("statuses", listDealStatus);
        req.setAttribute("listCurrency", listCurrency);
        req.setAttribute("companies", listCompany);
        req.getRequestDispatcher("/pages/addContact.jsp").forward(req, resp);
    }
}
