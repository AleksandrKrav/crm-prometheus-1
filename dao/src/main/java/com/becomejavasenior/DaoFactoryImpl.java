package com.becomejavasenior;

import com.becomejavasenior.daoContact.ContactDao;
import com.becomejavasenior.daoContact.ContactDaoImpl;
import com.becomejavasenior.impl.*;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DaoFactoryImpl implements DaoFactory {
    private static final Logger log = Logger.getLogger(DaoFactoryImpl.class);

    private Properties propDB = this.setProperties("dao.properties");
    private final String url = propDB.getProperty("db.url");
    private final String user = propDB.getProperty("db.user");
    private final String password = propDB.getProperty("db.password");
    private final String driver = propDB.getProperty("db.driver");

    public DaoFactoryImpl() {
        log.info("Enter to DaoFactoryImpl()");
        try {
            log.warn("Rigister driver");
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            log.error("Driver didn`t registered", e);
        }

    }

    @Override
    public Connection getConnection() throws SQLException {
        log.info("Enter to getConnection()");
        log.warn("Getting connection from DriverManager");
        return DriverManager.getConnection(url, user, password);
    }

    @Override
    public void closeConnection(Connection connection) throws SQLException {
        log.info("Enter to closeConnection()");
        try {
            log.warn("Check connection for null");
            if (connection != null) {
                connection.close();
                log.info("connection close");
            }
        } catch (Exception e) {
            log.error("Exception in close connection", e);
        }
    }


    @Override
    public UserDao getUserDao(Connection connection) throws PersistException {
//        return new UserDaoImpl(connection);
        return null;
    }

    @Override
    public ContactDao getContactDao(Connection connection) throws PersistException {
//        return new ContactDaoImpl(connection);
        return null;
    }

    @Override
    public CompanyDao getCompanyDao(Connection connection) {
//        return new CompanyDaoImpl(connection);
        return null;
    }

    @Override
    public DealDao getDealDao(Connection connection) {
//        return new DealDaoImpl(connection);
        return null;
    }

    @Override
    public DashboardDAO getDashboardDAO(Connection connection) throws PersistException {
//        return new DashboardDAOImpl(connection);
        return null;
    }

    @Override
    public TaskDao getTaskDao(Connection connection) throws PersistException {
//        return new TaskDaoImpl(connection);
        return null;
    }


    public Properties setProperties(String fileName) {
        log.info("Enter to setProperties()");
        Properties prop = new Properties();
        log.warn("Read properties to InputStream");
        try {
            log.warn("Load strings to properties");
            prop.load(getClass().getClassLoader().getResourceAsStream(fileName));
        } catch (IOException e) {
            log.error("Exception with read file", e);
        }
        return prop;
    }

}