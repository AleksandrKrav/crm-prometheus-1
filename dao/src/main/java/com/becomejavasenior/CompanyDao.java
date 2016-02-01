package com.becomejavasenior;

import java.util.List;
import java.util.Set;


public interface CompanyDao extends GenericDao<Company> {

    User getUser(int key) throws PersistException;

    List<User> getAllUsers() throws PersistException;

    List<PhoneType> getAllPhoneType() throws PersistException;

    List<Contact> getAllContacts() throws PersistException;

    List<Position> getAllPositions() throws PersistException;

}
