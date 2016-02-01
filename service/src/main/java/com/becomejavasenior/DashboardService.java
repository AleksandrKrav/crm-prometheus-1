package com.becomejavasenior;

import com.becomejavasenior.JDBCTemplate.DashboardJDBCTemplate;

/**
 * Created by Artem Kozlenko <ajet5911@gmail.com> on 15.01.2016.
 */
public interface DashboardService {

    public Integer getAmountFieldDB(String getAmountQuery);

    public Double getTotalDeal();

    public DashboardJDBCTemplate getDashboardJDBCTemplate();

}
