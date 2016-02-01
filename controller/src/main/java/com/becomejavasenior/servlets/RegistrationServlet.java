package com.becomejavasenior.servlets;

import com.becomejavasenior.ServiceException;
import com.becomejavasenior.User;
import com.becomejavasenior.UserService;
import com.becomejavasenior.config.ApplicationContextConfig;
import com.becomejavasenior.impl.UserServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationContextConfig.class);
        userService = context.getBean(UserService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/pages/login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("post");
        if (req.getParameter("registr") != null){
            User user = new User();
            user.setName(req.getParameter("username"));
            user.setLogin(req.getParameter("username"));
            user.setPassword(req.getParameter("password"));
            user.setEmail(req.getParameter("email"));
            try {
                user.setRole(userService.readRole(1));
                userService.add(user);
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        }
        doGet(req, resp);
    }
}
