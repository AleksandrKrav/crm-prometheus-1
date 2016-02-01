package com.becomejavasenior.servlets;


import com.becomejavasenior.*;
import com.becomejavasenior.config.ApplicationContextConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

/**
 * Created by Shevchenko on 03.01.2016.
 */
public class DataUserServlet extends HttpServlet {

    private UserService userService;
    private String login;

    @Override
    public void init() throws ServletException {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationContextConfig.class);
        userService = context.getBean(UserService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            HttpSession session = req.getSession();
            login = (String)session.getAttribute("user");
            if (login == null){
                login = "Guest";
            }
            System.out.println("login = " + login);
        } catch (Exception e) {
            e.printStackTrace();
        }


        ObjectMapper mapper = new ObjectMapper();
        Writer writer = new StringWriter();
        mapper.writeValue(writer, login);
        resp.getWriter().print(writer.toString());
    }
}


