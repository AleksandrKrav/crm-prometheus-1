package com.becomejavasenior.impl;

import com.becomejavasenior.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DealServiceImpl implements DealService {
    private static final Logger log = Logger.getLogger(DealServiceImpl.class);

    @Autowired
    DealDao dealDao;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(Deal deal) throws ServiceException {
        log.info("Add deal in service");
        dealDao.persist(deal);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(Deal deal) throws ServiceException {
        log.info("Delete deal in service");
        dealDao.delete(deal);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(Deal deal) throws ServiceException {
        log.info("Update deal in service");
        dealDao.update(deal);
    }


    @Override
    public Deal getById(Integer id) throws ServiceException {
        log.info("Get deal by id  in service");
        return dealDao.getById(id);
    }

    @Override
    public List<Deal> getAll() throws ServiceException {
        log.info("Get all deals  in service");
        return dealDao.getAll();
    }

    @Override
    public List<DealStatus> getAllStatuses() throws ServiceException {
        log.info("Get all statuses  in service");
        return dealDao.getAllStatus();
    }

    @Override
    public List<Currency> getAllCurrencies() throws ServiceException {
        log.info("Get all currencies  in service");
        return dealDao.getAllCurrencies();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addContact(Deal deal, Contact contact) throws ServiceException {
        dealDao.addContact(deal, contact);
    }

    @Override
    public Currency readCurrency(Integer id) throws ServiceException {
        log.info("Get currency by id  in service");
        return dealDao.readCurrency(id);
    }

    @Override
    public DealStatus readDealStatus(int key) throws ServiceException {
        log.info("Get status by id  in service");
        return dealDao.readDealStatus(key);
    }

    @Override
    public List<Deal> getDealsByCompanyId(int key) throws ServiceException {
        log.info("Get deal company by id  in service");
        return dealDao.getDealsByCompanyId(key);
    }


}
