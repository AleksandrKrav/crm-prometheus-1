package com.becomejavasenior;


import com.becomejavasenior.daoContact.ContactDao;

import java.sql.Connection;
import java.sql.SQLException;

/** Factory of objects for work with database */
public interface DaoFactory {

    /** Returns the database connection */
    public Connection getConnection() throws SQLException;

    /** Close the database connection */
    public void closeConnection(Connection connection) throws SQLException;

    public  UserDao getUserDao(Connection connection) throws PersistException;

    public ContactDao getContactDao(Connection connection) throws PersistException;

    public  CompanyDao getCompanyDao(Connection connection) throws PersistException;

    public  DealDao getDealDao(Connection connection) throws PersistException;

    DashboardDAO getDashboardDAO(Connection connection) throws PersistException;

    public TaskDao getTaskDao(Connection connection) throws PersistException;

}