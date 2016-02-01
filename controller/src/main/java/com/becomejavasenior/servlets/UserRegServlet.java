package com.becomejavasenior.servlets;

import com.becomejavasenior.*;
import com.becomejavasenior.config.ApplicationContextConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
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
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;


@WebServlet(name = "UserRegServlet", urlPatterns = "/userReg")
public class UserRegServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(UserRegServlet.class);

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
                if ((userFromList.getLogin().equals(req.getParameter("login")))){
                    user = userFromList;
                    break;
                }
            }
            if (user == null){
                user = new User();
                user.setName(req.getParameter("name"));
                user.setLogin(req.getParameter("login"));
                user.setPassword(DigestUtils.md5Hex(req.getParameter("pass")));
                user.setEmail(req.getParameter("email"));
                user.setRole(userService.readRole(1));
                userService.add(user);
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        if (user != null){
            respList.add("Регистрация выполнена. Выполните вход");
        }else{
            respList.add("Логин " + req.getParameter("login") + " занят");
        }
        ObjectMapper mapper = new ObjectMapper();
        Writer writer = new StringWriter();
        mapper.writeValue(writer, respList);
        resp.getWriter().print(writer.toString());
    }
    }

