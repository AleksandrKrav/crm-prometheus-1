package com.becomejavasenior.JDBCTemplate;

import com.becomejavasenior.DashboardDAO;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Artem Kozlenko <ajet5911@gmail.com> on 14.01.2016.
 */
public class DashboardJDBCTemplate {
    private static final Logger log = Logger.getLogger(DashboardJDBCTemplate.class);
    private JdbcTemplate jdbcTemplateObject;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
        log.info("Set dataSource");
    }

    public Integer getAmountFieldDB(String getAmountQuery, String rsString){
        log.warn("Get amount field: query - " + getAmountQuery);
        Integer amountRow = jdbcTemplateObject.queryForObject(getAmountQuery, Integer.class);
        return amountRow;
    }

    public Double getTotalDeal(String getTotalDealQuery) {
        log.warn("Get total deal field: query - " + getTotalDealQuery);
        Double total = 0.0;
        total = jdbcTemplateObject.queryForObject(getTotalDealQuery, Double.class);
        return total;
    }

    public String getAmountDealQuery() {
        log.info("getAmountDealQuery");
        return "SELECT COUNT (*) FROM DEAL";
    }

    public String getSuccessDealQuery() {
        log.info("getSuccessDealQuery");
        return "SELECT COUNT(*) FROM DEAL INNER JOIN DEAL_STATUS ON " +
                "DEAL.DEAL_STATUS_ID=DEAL_STATUS.ID WHERE DEAL_STATUS.ID = 3";
    }

    public String getUnsuccessDealQuery() {
        log.info("getUnsuccessDealQuery");
        return "SELECT COUNT(*) FROM DEAL INNER JOIN DEAL_STATUS ON " +
                "DEAL.DEAL_STATUS_ID=DEAL_STATUS.ID WHERE DEAL_STATUS.ID = 4";
    }

    public String getDealWithoutTaskQuery() {
        log.info("getDealWithoutTaskQuery");
        return "SELECT (SELECT COUNT(DEAL.ID) FROM DEAL) - COUNT(DISTINCT DEAL.ID) FROM DEAL " +
                "INNER JOIN TASK ON DEAL.ID=TASK.DEAL_ID";
    }

    public String getDealWithTaskQuery() {
        log.info("getDealWithTaskQuery");
        return "SELECT COUNT(DISTINCT DEAL.ID) FROM DEAL INNER JOIN TASK ON " +
                "DEAL.ID=TASK.DEAL_ID";
    }

    public String getInProgressTaskQuery() {
        log.info("getInProgressTaskQuery");
        return "SELECT COUNT(*) FROM TASK INNER JOIN TASK_STATUS ON " +
                "TASK.TASK_STATUS_ID=TASK_STATUS.ID WHERE TASK_STATUS.ID=2";
    }

    public String getSuccessTaskQuery() {
        log.info("getSuccessTaskQuery");
        return "SELECT COUNT(*) FROM TASK INNER JOIN TASK_STATUS ON " +
                "TASK.TASK_STATUS_ID=TASK_STATUS.ID WHERE TASK_STATUS.ID=3";
    }

    public String getUnsuccessTaskQuery() {
        log.info("getUnsuccessTaskQuer");
        return "SELECT COUNT(*) FROM TASK INNER JOIN TASK_STATUS ON " +
                "TASK.TASK_STATUS_ID=TASK_STATUS.ID WHERE TASK_STATUS.ID=4";
    }

    public String getAmountCompanyQuery() {
        log.info("getAmountCompanyQuery");
        return "SELECT COUNT(*) FROM COMPANY";
    }

    public String getAmountContactQuery() {
        log.info("getAmountContactQuery");
        return "SELECT COUNT(*) FROM CONTACT";
    }

    public String getTotalDealQuery() {
        log.info("getTotalDealQuery");
        return "SELECT SUM(BUDGET) FROM DEAL";
    }
}
