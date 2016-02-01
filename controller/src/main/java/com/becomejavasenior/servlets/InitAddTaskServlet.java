package com.becomejavasenior.servlets;

import com.becomejavasenior.*;
import com.becomejavasenior.config.ApplicationContextConfig;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * @author Shevchenko on 29.11.2015.
 */

public class InitAddTaskServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(InitAddTaskServlet.class);
    private UserService userService;
    private TaskService taskService;
    private DealService dealService;
    private List<Contact> listContact;
    private List<Deal> listDeal;
    private List<Company> listCompany;
    private List<User> listUser;
    private List<TaskType> listTaskType;



    @Override
    public void init() throws ServletException {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationContextConfig.class);
        userService = context.getBean(UserService.class);
        dealService = context.getBean(DealService.class);
        taskService = context.getBean(TaskService.class);

    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //get connection, userDAO, contactDAO, taskDAO, dealDAO

        try {
            listUser = userService.getAll();
            listTaskType = taskService.getAllTaskType();

            //simmulation work CompanyDAO
            Company company1 = new Company();
            company1.setId(0);
            company1.setName("company1");
            Company company2 = new Company();
            company2.setId(1);
            company2.setName("company2");
            listCompany = new ArrayList<Company>(Arrays.asList(company1, company2));
        }  catch (ServiceException e) {
            e.printStackTrace();
        }

        req.setAttribute("listUser", listUser);
        req.setAttribute("listTaskType", listTaskType);
        req.setAttribute("listCompany", listCompany);
        req.getRequestDispatcher("/pages/addTask.jsp").forward(req, resp);
    }

    @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
