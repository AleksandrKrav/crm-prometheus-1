package com.becomejavasenior.servlets;

import com.becomejavasenior.User;
import com.becomejavasenior.UserRightService;
import com.becomejavasenior.impl.UserRightServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserAdd", urlPatterns = "/user_add")
public class UserAddServlet extends HttpServlet {

    UserRightService userRightService;

    @Override
    public void init() throws ServletException {
        userRightService = new UserRightServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("user_add") != null) {
            User user = new User();
            user.setName(req.getParameter("username"));
            user.setLogin(req.getParameter("username"));
            user.setPassword(req.getParameter("password"));
            user.setEmail(req.getParameter("email"));
            if (req.getParameter("user_admin") != null){
                user.setRole(userRightService.getRoleById(1));
            } else {
                user.setRole(userRightService.getRoleById(2));
            }
            userRightService.create(user);
        }
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
