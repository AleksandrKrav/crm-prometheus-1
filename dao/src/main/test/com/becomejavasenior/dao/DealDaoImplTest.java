package com.becomejavasenior.dao;

import com.becomejavasenior.*;
import com.becomejavasenior.impl.CompanyDaoImpl;
import com.becomejavasenior.impl.DealDaoImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;


public class DealDaoImplTest {
    private static DaoFactory factory = new DaoFactoryImpl();

    private Connection connection;

    @Before
    public void setUp() throws SQLException {
        connection = factory.getConnection();
//        connection.setAutoCommit(false);
    }

    @After
    public void tearDown() throws SQLException {
////        connection.rollback();
        connection.close();
    }

    @Test
    public void testCreate() throws Exception {
        DealDao dealDao = factory.getDealDao(connection);
        Deal deal = new Deal();

        deal.setDate(new Date());
        deal.setName("Sdelka");
        deal.setBudget(BigDecimal.valueOf(2014.23));

        DealStatus dealStatus = new DealStatus();
        dealStatus.setId(1);
        deal.setStatus(dealStatus);

        User u = new User();
        u.setId(1);
        deal.setOwner(u);

        Currency currency = new Currency();
        currency.setId(1);
        deal.setCurrency(currency);

        Company company = new Company();
        company.setId(1);
        deal.setCompany(company);

        dealDao.persist(deal);

    }

    @Test
    public void testRead() throws Exception {
        DealDaoImpl dealDao = (DealDaoImpl) factory.getDealDao(connection);
        Deal deal;
        deal = dealDao.getById(1);
        System.out.println(deal.toString());

//        CompanyDaoImpl c = (CompanyDaoImpl) factory.getCompanyDao(connection);
//        Company com = c.getById(1);
//        System.out.println(com.toString());
    }

    @Test
    public void testUpdate() {
        DealDaoImpl dealDao = (DealDaoImpl) factory.getDealDao(connection);
        Deal deal = dealDao.getById(1);
        deal.setName("Allaz");
        dealDao.update(deal);
    }
    @Test
    public void testGetAll() throws Exception {
        DealDaoImpl dealDao = (DealDaoImpl) factory.getDealDao(connection);
        List<Deal> dealList = new ArrayList<>();
        dealList = dealDao.getAll();
        for (Deal d : dealList){
            System.out.println(d);
        }
    }

    @Test
    public void testDelete() throws Exception {
        DealDaoImpl dealDao = (DealDaoImpl) factory.getDealDao(connection);
        Deal d = new Deal();
        d.setId(3);
        dealDao.delete(d);
    }

}