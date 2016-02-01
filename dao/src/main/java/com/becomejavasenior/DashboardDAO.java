package com.becomejavasenior;

/**
 * Created by shevchenko on 09.12.2015.
 */
public interface DashboardDAO {

    public Integer getAmountFieldDB(String getAmountQuery, String rsString) throws PersistException;
    public Double getTotalDeal(String getTotalDealQuery) throws PersistException;

    public String getAmountDealQuery();
    public String getSuccessDealQuery();
    public String getUnsuccessDealQuery();
    public String getDealWithoutTaskQuery();
    public String getDealWithTaskQuery();
    public String getInProgressTaskQuery();
    public String getSuccessTaskQuery();
    public String getUnsuccessTaskQuery();
    public String getAmountCompanyQuery();
    public String getAmountContactQuery();
    public String getTotalDealQuery();

}
