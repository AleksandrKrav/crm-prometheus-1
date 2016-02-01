package com.becomejavasenior;

import java.util.List;

public interface CompanyService extends GenericService<Company, CompanyDao>  {

    List<User> getAllUsers() throws ServiceException;

    List<PhoneType> getAllPhoneType() throws ServiceException;

    List<Contact> getAllContacts() throws ServiceException;

    List<Position> getAllPositions() throws ServiceException;
}
