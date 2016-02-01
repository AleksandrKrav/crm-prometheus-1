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

@WebServlet("/list_of_deals")
public class ListOfDealsServlet extends HttpServlet {
    private DealService dealService;

    @Override
    public void init() throws ServletException {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationContextConfig.class);
        dealService = context.getBean(DealService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Deal> deals = null;
        Contact contact = null;
        try {
            deals = dealService.getAll();

            contact = deals.get(0).getContacts().get(0);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        req.setAttribute("contact", contact.getName());
        req.setAttribute("deals", deals);
        req.getRequestDispatcher("/pages/listOfDeals.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }


}
