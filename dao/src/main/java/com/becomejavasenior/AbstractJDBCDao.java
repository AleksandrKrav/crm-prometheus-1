package com.becomejavasenior;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

public abstract class AbstractJDBCDao<T extends Identified> implements GenericDao<T> {
    private static final Logger log = Logger.getLogger(AbstractJDBCDao.class);

//    private DataSource dataSource;
    protected Connection connection;

    public AbstractJDBCDao(DataSource dataSource) {
        try {
            this.connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() throws SQLException {
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




    /**
     * Returns sql query for get all record.
     * <p>
     * SELECT * FROM [Table]
     */
    public abstract String getSelectQuery();

    /**
     * Returns sql query for insert new records to database
     * <p>
     * INSERT INTO [Table] ([column, column, ...]) VALUES (?, ?, ...);
     */
    public abstract String getCreateQuery();

    /**
     * Returns sql query for update record.
     * <p>
     * UPDATE [Table] SET [column = ?, column = ?, ...] WHERE id = ?;
     */
    public abstract String getUpdateQuery();

    /**
     * Returns sql query for delete record from database.
     * <p>
     * DELETE FROM [Table] WHERE id= ?;
     */
    public abstract String getDeleteQuery();

    /**
     * Analyzes ResultSet and returns list of objects
     */
    protected abstract List<T> parseResultSet(ResultSet rs) throws PersistException;

    /**
     * Prepare statement for insert query
     */
    protected abstract void prepareStatementForInsert(PreparedStatement statement, T object) throws PersistException;

    /**
     * Prepare statement for update query
     */
    protected abstract void prepareStatementForUpdate(PreparedStatement statement, T object) throws PersistException;

    /**
     * Insert or update extended tables records for object 'object'
     */
    protected void insertUpdateExtendedTableRecords(T object) throws PersistException {
    }

    /**
     * Deletes extended tables records for object 'object'
     */
    protected void deleteExtendedTableRecords(T object) throws PersistException {
    }


    /**
     * Returns object from database with primary key 'key' or 'null'
     */
    @Override
    public T getById(Integer key) throws PersistException {
        log.info("Enter to getById()");
        List<T> list = null;
        log.warn("Execute getSelectQuery()");
        String sql = getSelectQuery();
        sql += "WHERE is_deleted=FALSE AND id = ?";
        log.warn("Use prepared statement");
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, key);
            log.warn("Executing statement query");
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            log.error("Error with preparedStatement of ResultSet", new PersistException(e));
//            throw new PersistException(e);
        }

        if (list.size() > 1) {
            log.error("Received more than one record.", new PersistException("Received more than one record."));
        }
        return list.iterator().next();
    }

    /**
     * Returns list of objects from database
     */
    @Override
    public List<T> getAll() throws PersistException {
        log.info("Enter to getAll()");
        List<T> list = null;
        log.warn("Execute getSelectQuery()");
        String sql = getSelectQuery() + " WHERE is_deleted=FALSE";
        log.warn("Use prepared statement");
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            log.warn("Executing statement query");
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            log.error("Error with preparedStatement of ResultSet", new PersistException(e));
        }
        return list;
    }

    @Override
    /** Inserts object to database and returns 'id' */
    public Integer persist(T object) throws PersistException {
        log.info("Enter to persist()");
        T persistInstance;
        int auto_id = 0;
        log.warn("Execute getCreateQuery()");
        String sql = getCreateQuery();
        log.warn("Use prepared statement");
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            log.warn("Executing  prepareStatementForInsert()");
            prepareStatementForInsert(statement, object);
            log.warn("Executing statement query");
            int count = statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            auto_id = rs.getInt(1);
            object.setId(auto_id);
            log.warn("Insert extended tables");
            insertUpdateExtendedTableRecords(object);
            if (count != 1) {
                log.error("On persist modify more then 1 record", new PersistException("More then 1 record: " + count));
            }
        } catch (Exception e) {
            log.error("Error with preparedStatement of ResultSet", new PersistException(e));
        }
        return auto_id;
    }

    /**
     * Updates object to database
     */
    @Override
    public void update(T object) throws PersistException {
        log.info("Enter to update()");
        log.warn("Execute getUpdateQuery()");
        String sql = getUpdateQuery();
        log.warn("Use prepared statement");
        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            log.warn("Executing  prepareStatementForUpdate()");
            prepareStatementForUpdate(statement, object); // заполнение аргументов запроса оставим на совесть потомков
            log.warn("Executing statement query");
            int count = statement.executeUpdate();
            if (count != 1) {
                log.error("On persist modify more then 1 record", new PersistException("More then 1 record: " + count));
            }
        } catch (Exception e) {
            log.error("Error with preparedStatement of ResultSet", new PersistException(e));
        }
    }

    /**
     * Deletes object from database with primary key 'key' or 'null'
     */
    @Override
    public void delete(T object) throws PersistException {
        log.info("Enter to delete()");
        String sql = getDeleteQuery();
        log.warn("Use prepared statement");
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, object.getId());
            log.warn("Executing statement query");
            int count = statement.executeUpdate();
            log.warn("Insert extended tables");
            deleteExtendedTableRecords(object);
            if (count != 1) {
                log.error("On persist modify more then 1 record", new PersistException("More then 1 record: " + count));
            }
        } catch (Exception e) {
            log.error("Error with preparedStatement of ResultSet", new PersistException(e));
        }
    }


}
