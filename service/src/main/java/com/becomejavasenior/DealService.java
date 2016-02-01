package com.becomejavasenior;

import java.util.List;

public interface DealService extends GenericService<Deal, DealDao>{
    Currency readCurrency(Integer id) throws ServiceException;
    DealStatus readDealStatus(int key) throws ServiceException;
    List<Deal> getDealsByCompanyId(int key) throws ServiceException;
    List<DealStatus> getAllStatuses() throws ServiceException;
    List<Currency> getAllCurrencies() throws ServiceException;
    void addContact(Deal deal, Contact contact) throws ServiceException;
}
