package com.becomejavasenior.impl;

import com.becomejavasenior.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Artem Kozlenko <ajet5911@gmail.com> on 13.01.2016.
 */

@Service
public class CompanyServiceImpl implements CompanyService {
    private static final Logger log = Logger.getLogger(CompanyServiceImpl.class);

    @Autowired
    private CompanyDao companyDao;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(Company company) throws ServiceException {
        log.info("Add company in service");
        companyDao.persist(company);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(Company company) throws ServiceException {
        log.info("Delete company in service");
        companyDao.delete(company);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(Company company) throws ServiceException {
        log.info("Update company in service");
        companyDao.update(company);
    }

    @Override
    public Company getById(Integer id) throws ServiceException {
        log.info("Get company by id  in service");
        return companyDao.getById(id);
    }

    @Override
    public List<Company> getAll() throws ServiceException {
        log.info("Get all companies  in service");
        return companyDao.getAll();
    }

    @Override
    public List<User> getAllUsers() throws ServiceException {
        log.info("Get all users  in service");
        return companyDao.getAllUsers();
    }

    @Override
    public List<PhoneType> getAllPhoneType() throws ServiceException {
        log.info("Get all phone types  in service");
        return companyDao.getAllPhoneType();
    }

    @Override
    public List<Contact> getAllContacts() throws ServiceException {
        log.info("Get all contacts  in service");
        return companyDao.getAllContacts();
    }

    @Override
    public List<Position> getAllPositions() throws ServiceException {
        log.info("Get all positions  in service");
        return companyDao.getAllPositions();
    }
}
