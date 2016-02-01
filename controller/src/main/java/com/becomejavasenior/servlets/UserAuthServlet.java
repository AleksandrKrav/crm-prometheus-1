package com.becomejavasenior.servlets;

import com.becomejavasenior.*;
import com.becomejavasenior.config.ApplicationContextConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.LinkedList;

public class UserAuthServlet extends HttpServlet{
    private static final Logger log = Logger.getLogger(UserAuthServlet.class);

    private UserService userService;
    private Cookie userCookie;

    @Override
    public void init() throws ServletException {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationContextConfig.class);
        userService = context.getBean(UserService.class);
        userCookie = new Cookie("user", "Guest");
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
                    if (userFromList.getPassword().equals(DigestUtils.md5Hex(req.getParameter("pass")))){
                        HttpSession session = req.getSession();
                        session.setAttribute("user", userFromList.getLogin());
                        //setting session to expiry in 30 mins
                        session.setMaxInactiveInterval(30*60);
                        req.setAttribute("user", userFromList.toString());
                        user = userFromList;
                        break;
                    }
                }
            }
        }  catch (ServiceException e) {
            e.printStackTrace();
        }

            if (user != null){
                userCookie.setValue(user.getName());
                resp.addCookie(userCookie);
                respList.add("Вход выполнен");
            }else{
                respList.add("Неверный логин или пароль");
                respList.add("Гость");
            }

            ObjectMapper mapper = new ObjectMapper();
            Writer writer = new StringWriter();
            mapper.writeValue(writer, respList);
            resp.getWriter().print(writer.toString());
    }
}
