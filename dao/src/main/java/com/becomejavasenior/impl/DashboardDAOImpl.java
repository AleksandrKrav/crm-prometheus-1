package com.becomejavasenior.impl;

import com.becomejavasenior.AbstractJDBCDao;
import com.becomejavasenior.DashboardDAO;
import com.becomejavasenior.PersistException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.HashMap;
import java.util.List;


/**
 * Created by shevchenko on 09.12.2015.
 */
public class DashboardDAOImpl implements DashboardDAO {
    private static final Logger log = Logger.getLogger(DashboardDAOImpl.class);
    private Connection connection;

    public DashboardDAOImpl(Connection connection){
        this.connection = connection;
        log.info("Init DashboardDAO");
    }

    @Override
    public Integer getAmountFieldDB(String getAmountQuery, String rsString){
        log.warn("Get field: query - " + getAmountQuery);
        Integer amountRow = 0;
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(getAmountQuery);
            if (rs.next()){
                amountRow = Integer.parseInt(rs.getString(rsString));
            }
        } catch (SQLException e) {
            log.error("Exception in getAmountFieldDB, Query = " + getAmountQuery, new PersistException());
        }finally {
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return amountRow;
    }

    @Override
    public Double getTotalDeal(String getTotalDealQuery) {
        log.warn("Get field: query - " + getTotalDealQuery);
        Double total = 0.0;
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(getTotalDealQuery);
            if (rs.next()){
                total = Double.parseDouble(rs.getString("SUM"));
            }
        } catch (SQLException e) {
            log.error("Exception in getTotalDeal, Query = " + getTotalDealQuery, new PersistException());
        }
        finally {
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return total;
    }

    @Override
    public String getAmountDealQuery() {
        log.info("getAmountDealQuery");
        return "SELECT COUNT (*) FROM DEAL";
    }

    @Override
    public String getSuccessDealQuery() {
        log.info("getSuccessDealQuery");
        return "SELECT COUNT(*) FROM DEAL INNER JOIN DEAL_STATUS ON " +
                "DEAL.DEAL_STATUS_ID=DEAL_STATUS.ID WHERE DEAL_STATUS.ID = 3";
    }

    @Override
    public String getUnsuccessDealQuery() {
        log.info("getUnsuccessDealQuery");
        return "SELECT COUNT(*) FROM DEAL INNER JOIN DEAL_STATUS ON " +
                "DEAL.DEAL_STATUS_ID=DEAL_STATUS.ID WHERE DEAL_STATUS.ID = 4";
    }

    @Override
    public String getDealWithoutTaskQuery() {
        log.info("getDealWithoutTaskQuery");
        return "SELECT (SELECT COUNT(DEAL.ID) FROM DEAL) - COUNT(DISTINCT DEAL.ID) FROM DEAL " +
                "INNER JOIN TASK ON DEAL.ID=TASK.DEAL_ID";
    }

    @Override
    public String getDealWithTaskQuery() {
        log.info("getDealWithTaskQuery");
        return "SELECT COUNT(DISTINCT DEAL.ID) FROM DEAL INNER JOIN TASK ON " +
                "DEAL.ID=TASK.DEAL_ID";
    }

    @Override
    public String getInProgressTaskQuery() {
        log.info("getInProgressTaskQuery");
        return "SELECT COUNT(*) FROM TASK INNER JOIN TASK_STATUS ON " +
                "TASK.TASK_STATUS_ID=TASK_STATUS.ID WHERE TASK_STATUS.ID=2";
    }

    @Override
    public String getSuccessTaskQuery() {
        log.info("getSuccessTaskQuery");
        return "SELECT COUNT(*) FROM TASK INNER JOIN TASK_STATUS ON " +
                "TASK.TASK_STATUS_ID=TASK_STATUS.ID WHERE TASK_STATUS.ID=3";
    }

    @Override
    public String getUnsuccessTaskQuery() {
        log.info("getUnsuccessTaskQuer");
        return "SELECT COUNT(*) FROM TASK INNER JOIN TASK_STATUS ON " +
                "TASK.TASK_STATUS_ID=TASK_STATUS.ID WHERE TASK_STATUS.ID=4";
    }

    @Override
    public String getAmountCompanyQuery() {
        log.info("getAmountCompanyQuery");
        return "SELECT COUNT(*) FROM COMPANY";
    }

    @Override
    public String getAmountContactQuery() {
        log.info("getAmountContactQuery");
        return "SELECT COUNT(*) FROM CONTACT";
    }

    @Override
    public String getTotalDealQuery() {
        log.info("getTotalDealQuery");
        return "SELECT SUM(BUDGET) FROM DEAL";
    }



}
