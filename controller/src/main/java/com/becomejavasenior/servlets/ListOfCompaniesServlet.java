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

/**
 * Created by Artem Kozlenko <ajet5911@gmail.com> on 07.01.2016.
 */
@WebServlet("/list_of_companies")

public class ListOfCompaniesServlet extends HttpServlet {
    private CompanyService companyService;

    @Override
    public void init() throws ServletException {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationContextConfig.class);
        companyService = context.getBean(CompanyService.class);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Company> companies = null;
        try {
            companies = companyService.getAll();
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        req.setAttribute("companies", companies);
        req.getRequestDispatcher("/pages/listOfCompanies.jsp").forward(req, resp);

    }

}
