package com.becomejavasenior.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class LogoutUserServlet extends HttpServlet {
    private Cookie[] cookies;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if(session != null){
            session.invalidate();
        }
        resp.addCookie(new Cookie("user","Guest"));
        resp.sendRedirect("pages/auth.jsp");
    }
}
