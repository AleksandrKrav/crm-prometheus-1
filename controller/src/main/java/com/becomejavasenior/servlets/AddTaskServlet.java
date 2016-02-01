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
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * @author Shevchenko on 05.12.2015.
 */
public class AddTaskServlet extends HttpServlet{
    private static final Logger log = Logger.getLogger(AddTaskServlet.class);
    private TaskService taskService;
    private Task task;

    @Override
    public void init() throws ServletException {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationContextConfig.class);
        taskService = context.getBean(TaskService.class);    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            task = new Task();
            Calendar gc = new GregorianCalendar();
            task.setDateOfCreate(gc.getTime());
            gc.setTime(new Date(Long.parseLong(req.getParameter("finishDate"))));
            task.setFinishDate(gc.getTime());
            task.setDescription(req.getParameter("commentTask"));
            if (req.getParameter("nameTask") != null) {
                switch (req.getParameter("nameTask")) {
                    case "contact": {
                        //task.setContact(daoContact.read(Integer.parseInt(req.getParameter("idNameTask"))));
                        break;
                    }
                    case "deal": {
                        //task.setDeal(daoDeal.read(Integer.parseInt(req.getParameter("idNameTask"))));
                        break;
                    }
                    case "company": {
                        //task.setCompany(daoCompany.read(Integer.parseInt(req.getParameter("idNameTask")));
                        break;
                    }
                }
            }
            task.setType(taskService.readTaskType(Integer.parseInt(req.getParameter("typeTask"))));
            task.setOwner(taskService.readUser(Integer.parseInt(req.getParameter("responsibleTask"))));
            task.setStatus(taskService.readTaskStatus(1));
            taskService.add(task);

            if(req.getParameter("ajax") != null) {
                System.out.println("ajax = " + req.getParameter("ajax"));
                //req.getRequestDispatcher("/index.jsp").forward(req, resp);
            }else{
                System.out.println("ajax = " + req.getParameter("ajax"));
                req.getRequestDispatcher("/index.jsp").forward(req, resp);
            }
        }  catch (ServiceException e) {
            e.printStackTrace();
        }

    }
}
