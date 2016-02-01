package com.becomejavasenior.servlets;

import com.becomejavasenior.*;
import com.becomejavasenior.config.ApplicationContextConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Shevchenko on 10.01.2016.
 */

@WebServlet("/loginUser")
public class LoginUserServlet extends HttpServlet{

    private UserService userService;

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
        ArrayList respList = new ArrayList();
        User user = null;
        try {
            LinkedList<User> userList = (LinkedList<User>)userService.getAll();
            for (User userFromList:userList){
                if (userFromList.getLogin().equals(req.getParameter("login"))){
                    if (userFromList.getPassword().equals(req.getParameter("password"))){
                        System.out.println("Authorisation done");
                        HttpSession session = req.getSession();
                        session.setAttribute("user", userFromList.getLogin());
                        //setting session to expiry in 30 mins
                        session.setMaxInactiveInterval(30*60);
                        req.setAttribute("user", userFromList.toString());
                        req.setAttribute("sessionID", session.getId());
                        System.out.println("session ID = " + session.getId());
                        user = userFromList;
                        break;
                    }
                }
            }

        }  catch (ServiceException e) {
            e.printStackTrace();
        }

        if (user != null){
            respList.add("Good");
            respList.add(user.getLogin());
        }else{
            respList.add("Login or Password incorrect");
            respList.add("Guest");
        }
        ObjectMapper mapper = new ObjectMapper();
        Writer writer = new StringWriter();
        mapper.writeValue(writer, respList);
        resp.getWriter().print(writer.toString());
    }
}
