package com.becomejavasenior.impl;

import com.becomejavasenior.DashboardService;
import com.becomejavasenior.JDBCTemplate.DashboardJDBCTemplate;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Artem Kozlenko <ajet5911@gmail.com> on 15.01.2016.
 */
public class DashboardServiceImpl implements DashboardService {
    private static final Logger log = Logger.getLogger(DashboardServiceImpl.class);
    private ApplicationContext context = new ClassPathXmlApplicationContext("Context.xml");
    private DashboardJDBCTemplate dashboardJDBCTemplate = (DashboardJDBCTemplate)context.getBean("dashboardJDBCTemplate");

    @Override
    public Integer getAmountFieldDB(String getAmountQuery) {
        log.warn("Get amount field in service");
        return dashboardJDBCTemplate.getAmountFieldDB(getAmountQuery, "COUNT");
    }

    @Override
    public Double getTotalDeal() {
        log.warn("Get amount field in service");
        return dashboardJDBCTemplate.getTotalDeal(dashboardJDBCTemplate.getTotalDealQuery());
    }

    public DashboardJDBCTemplate getDashboardJDBCTemplate() {
        log.warn("Get DashboardJDBCTemplate in service");
        return dashboardJDBCTemplate;
    }
}
