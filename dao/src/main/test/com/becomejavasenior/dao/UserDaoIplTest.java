package com.becomejavasenior.dao;

import com.becomejavasenior.*;
import com.becomejavasenior.impl.DealDaoImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Ignore
public class UserDaoIplTest {
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
        UserDao userDao = factory.getUserDao(connection);
        User us = new User();
        us.setName("User");
        us.setLogin("user");
        us.setPassword("12344");
        us.setEmail("123");
        us.setRole(userDao.readRole(1));
    }

    @Test
    public void testGetAll() throws Exception {
        UserDao userDao = factory.getUserDao(connection);
        List<User> users = new ArrayList<>();
        users = userDao.getAll();
        for (User us : users){
            System.out.println(us);
        }
    }
}
