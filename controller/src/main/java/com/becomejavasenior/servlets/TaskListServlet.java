package com.becomejavasenior.servlets;

import com.becomejavasenior.*;
import com.becomejavasenior.config.ApplicationContextConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Sergey Lugovoy <svlugovoy@gmail.com> 29.12.2015.
 */
@WebServlet(name = "TaskListServlet", urlPatterns = "/tasklist")
public class TaskListServlet extends HttpServlet {

    private TaskService taskService;

    @Override
    public void init() throws ServletException {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationContextConfig.class);
        taskService = context.getBean(TaskService.class);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Task> tasks = null;
        try {
            tasks = taskService.getAll();
            request.setAttribute("tasks", tasks);
            request.getRequestDispatcher("/pages/tasklist.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //
        //
        //getServletContext().getRequestDispatcher("/pages/tasklist.jsp").forward(request, response);
        //response.sendRedirect("/pages/tasklist.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
