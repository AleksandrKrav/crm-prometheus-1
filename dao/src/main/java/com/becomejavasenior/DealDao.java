package com.becomejavasenior;

import java.util.List;

public interface DealDao extends GenericDao<Deal>{
    Currency readCurrency(int key) throws PersistException ;
    DealStatus readDealStatus(int key) throws PersistException ;
    List<DealStatus> getAllStatus() throws PersistException;
    List<Contact> readContacts(Deal deal) throws PersistException;
    Position readPosition(int key) throws PersistException;
    List<Deal> getDealsByCompanyId(int key) throws PersistException;
    List<Currency> getAllCurrencies() throws PersistException;
    void addContact(Deal deal, Contact contact) throws PersistException;
    User getUser(int key) throws PersistException;
    Company readCompany(int key) throws PersistException;
}
