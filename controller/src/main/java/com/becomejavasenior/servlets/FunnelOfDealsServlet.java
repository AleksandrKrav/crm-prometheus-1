package com.becomejavasenior.servlets;

import com.becomejavasenior.*;
import com.becomejavasenior.config.ApplicationContextConfig;
import com.becomejavasenior.impl.DealServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FunnelOfDealsServlet extends HttpServlet {
//    ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationContextConfig.class);
    private DealService dealService;

    @Override
    public void init() throws ServletException {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationContextConfig.class);
        dealService = context.getBean(DealService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Deal> deals = null;
        List<Deal> dealsForFirstContact = new ArrayList<>();
        List<Deal> dealsForConversation = new ArrayList<>();
        List<Deal> dealsForMakeDecisions = new ArrayList<>();
        List<Deal> dealsForMatchingContracts = new ArrayList<>();
        int[] countsOfDeals = new int[4];
        double[] countsOfBudget = new double[4];
        try {
            deals = dealService.getAll();
            for (Deal d : deals) {
                switch (d.getStatus().getName()) {
                    case "Первичный контакт":
                        dealsForFirstContact.add(d);
                        countsOfDeals[0]++;
                        countsOfBudget[0] += Double.valueOf(String.valueOf(d.getBudget()));
                        break;
                    case "Переговоры":
                        dealsForConversation.add(d);
                        countsOfDeals[1]++;
                        countsOfBudget[1] += Double.valueOf(String.valueOf(d.getBudget()));
                        break;
                    case "Принимают решение":
                        dealsForMakeDecisions.add(d);
                        countsOfDeals[2]++;
                        countsOfBudget[2] += Double.valueOf(String.valueOf(d.getBudget()));
                        break;
                    case "Согласование договора":
                        dealsForMatchingContracts.add(d);
                        countsOfDeals[3]++;
                        countsOfBudget[3] += Double.valueOf(String.valueOf(d.getBudget()));
                        break;
                }
            }
            }catch(ServiceException e){
                e.printStackTrace();
            }

        req.setAttribute("currency", deals.get(0).getCurrency().getName());
        req.setAttribute("countsOfDeals", countsOfDeals);
        req.setAttribute("countsOfBudget", countsOfBudget);

        req.setAttribute("dealsForFirstContact", dealsForFirstContact);
        req.setAttribute("dealsForConversation", dealsForConversation);
        req.setAttribute("dealsForMakeDecisions", dealsForMakeDecisions);
        req.setAttribute("dealsForMatchingContracts", dealsForMatchingContracts);

        req.getRequestDispatcher("/pages/funnelOfDeals.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
