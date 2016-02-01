package com.becomejavasenior.servlets;

import com.becomejavasenior.ServiceException;
import com.becomejavasenior.User;
import com.becomejavasenior.UserService;
import com.becomejavasenior.config.ApplicationContextConfig;
import com.becomejavasenior.impl.UserServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HeaderServlet extends HttpServlet {

    private UserService userService;
    private String username;
    private User user;
    private String email = getPrincipal();

    @Override
    public void init() throws ServletException {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationContextConfig.class);
        userService = context.getBean(UserService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            user = userService.getUserByEmail(email);
            username = user.getName();
        } catch (ServiceException e) {
        }

        req.setAttribute("user_name", username);

        req.getRequestDispatcher("pages/header.jsp").include(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    private String getPrincipal() {
        String userEmail;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            userEmail = ((UserDetails) principal).getUsername();
        } else {
            userEmail = principal.toString();
        }
        return userEmail;
    }
}
