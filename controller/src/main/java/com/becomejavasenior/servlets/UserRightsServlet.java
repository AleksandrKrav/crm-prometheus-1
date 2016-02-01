package com.becomejavasenior.servlets;

import com.becomejavasenior.Permition;
import com.becomejavasenior.Role;
import com.becomejavasenior.User;
import com.becomejavasenior.UserRightService;
import com.becomejavasenior.impl.UserRightServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserRights", urlPatterns = "/userRights")
public class UserRightsServlet extends HttpServlet {
    UserRightService userRightService;

    @Override
    public void init() throws ServletException {
        userRightService = new UserRightServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> userList;
        userList = userRightService.getAll();
        req.setAttribute("users", userList);
        req.getRequestDispatcher("/pages/userRights.jsp").forward(req, resp);
    }

}
