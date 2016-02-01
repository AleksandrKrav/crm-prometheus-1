package com.becomejavasenior.impl;

import com.becomejavasenior.*;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Sergey Lugovoy <svlugovoy@gmail.com> 21.12.2015.
 */
public class TaskDaoImpl extends AbstractJDBCDao<Task> implements TaskDao {

    private ExtendedTablesDao extendedTablesDao = new ExtendedTablesDao(connection);
    private static final Logger log = Logger.getLogger(TaskDaoImpl.class);

    public TaskDaoImpl(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public String getSelectQuery() {
        return "SELECT * FROM task ";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO task " +
                "(date_of_create, finish_date, description, company_id, deal_id, contact_id, owner_id, task_type_id, task_status_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE task SET " +
                "date_of_create=?, finish_date=?, description=?, company_id=?, deal_id=?, contact_id=?, owner_id=?, task_type_id=?, task_status_id=? " +
                "WHERE id=?;";
    }

    @Override
    public String getDeleteQuery() {
        return "UPDATE task SET is_deleted=TRUE " +
                "WHERE id=?;";
    }

    @Override
    protected List<Task> parseResultSet(ResultSet rs) throws PersistException {
        LinkedList<Task> result = new LinkedList<>();
        try {
            while (rs.next()) {
                Task task = new Task();
                task.setId(rs.getInt("id"));
                task.setDateOfCreate(rs.getTimestamp("date_of_create"));
                task.setFinishDate(rs.getTimestamp("finish_date"));
                task.setDescription(rs.getString("description"));
                task.setCompany(readCompany(rs.getInt("company_id")));
                task.setDeal(readDeal(rs.getInt("deal_id")));
                task.setContact(readContact(rs.getInt("contact_id")));
                task.setOwner(readUser(rs.getInt("owner_id")));
                task.setType(readTaskType(rs.getInt("task_type_id")));
                task.setStatus(readTaskStatus(rs.getInt("task_status_id")));
                //task.setComments(extendedTablesDao.getComments(task));

                result.add(task);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Task object) throws PersistException {
        try {
            statement.setTimestamp(1, new Timestamp(object.getDateOfCreate().getTime()));
            statement.setTimestamp(2, new Timestamp(object.getFinishDate().getTime()));
            statement.setString(3, object.getDescription());

             if (object.getCompany() == null) {
                statement.setObject(4, null);
            }else{
                 statement.setInt(4, object.getCompany().getId());
            }

            if (object.getDeal() == null){
                statement.setObject(5, null);
            }else{
                statement.setInt(5, object.getDeal().getId());
            }

            if (object.getContact() == null){
                statement.setObject(6, null);
            }else {
                statement.setInt(6, object.getContact().getId());
            }

            if (object.getOwner() == null){
                statement.setObject(7, null);
            }else{
                statement.setInt(7, object.getOwner().getId());
            }

            if (object.getType() == null){
                statement.setObject(8, null);
            }else{
                statement.setInt(8, object.getType().getId());
            }

            if (object.getStatus() == null){
                statement.setObject(9, null);
            }else{
                statement.setInt(9, object.getStatus().getId());
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Task object) throws PersistException {
        try {
            statement.setTimestamp(1, new Timestamp(object.getDateOfCreate().getTime()));
            statement.setTimestamp(2, new Timestamp(object.getFinishDate().getTime()));
            statement.setString(3, object.getDescription());
            if (object.getCompany() == null){
                statement.setObject(4, null);
            }else {
                statement.setInt(4, object.getCompany().getId());
            }
            if (object.getDeal() == null){
                statement.setObject(5, null);
            }else {
                statement.setInt(5, object.getDeal().getId());
            }
            if (object.getContact() == null){
                statement.setObject(6, null);
            }else {
                statement.setInt(6, object.getContact().getId());
            }
            statement.setInt(7, object.getOwner().getId());
            statement.setInt(8, object.getType().getId());
            statement.setInt(9, object.getStatus().getId());
            statement.setInt(10, object.getId());
            //extendedTablesDao.insertUpdateTableRecords(object, object.getComments(), false);
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    public Company readCompany(int key) throws PersistException {
        String sql = "SELECT * FROM company WHERE id=?;";
        Company company = new Company();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, key);
            ResultSet rs = statement.executeQuery();
            rs.next();
            company.setId(rs.getInt("id"));
            company.setName(rs.getString("name"));
            company.setAddress(rs.getString("address"));
            company.setWeb(rs.getString("web"));
            company.setEmail(rs.getString("email"));
            company.setOwner(readUser(rs.getInt("owner_id")));
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return company;
    }

    @Override
    public Deal readDeal(int key) throws PersistException {
        String sql = "SELECT * FROM deal WHERE id=?;";
        Deal deal = new Deal();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, key);
            ResultSet rs = statement.executeQuery();
            rs.next();
            deal.setId(rs.getInt("id"));
            deal.setDate(rs.getTimestamp("date_of_create"));
            deal.setName(rs.getString("name"));
            deal.setBudget(rs.getBigDecimal("budget"));
            deal.setStatus(readDealStatus(rs.getInt("deal_status_id")));
            deal.setCurrency(readCurrency(rs.getInt("currency_id")));
            deal.setOwner(readUser(rs.getInt("owner_id")));
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return deal;
    }

    public DealStatus readDealStatus(int key) throws PersistException {
        log.info("created sql request for readDealStatus to DealDaoImpl record");
        String sql = "SELECT * FROM deal_status WHERE id = ?;";
        DealStatus dealStatus = new DealStatus();
        log.warn("Give sql to prepared statement");
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, key);
            ResultSet rs = statement.executeQuery();
            rs.next();
            dealStatus.setId(rs.getInt("id"));
            dealStatus.setName(rs.getString("name"));
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return dealStatus;
    }

    public Currency readCurrency(int key) throws PersistException {
        log.info("created sql request for readCurrency to DealDaoImpl record");
        String sql = "SELECT * FROM currency WHERE id = ?;";
        Currency currency = new Currency();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, key);
            ResultSet rs = statement.executeQuery();
            rs.next();
            currency.setId(rs.getInt("id"));
            currency.setCode(rs.getString("code"));
            currency.setName(rs.getString("name"));
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return currency;
    }

    @Override
    public Contact readContact(int key) throws PersistException {
        String sql = "SELECT * FROM contact WHERE id = ?;";
        Contact contact = new Contact();
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, key);
            ResultSet rs = stm.executeQuery();
            rs.next();
            contact.setId(rs.getInt("id"));
            contact.setName(rs.getString("name"));
            contact.setPosition(readPosition(rs.getInt("positions_id")));
            contact.setCompany(readCompany(rs.getInt("company_id")));
            contact.setEmail(rs.getString("email"));
            contact.setSkype(rs.getString("skype"));
            contact.setOwner(readUser(rs.getInt("owner_id")));
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return contact;
    }

    public Position readPosition(int key) throws PersistException {
        String sql = "SELECT * FROM positions WHERE id = ?;";
        Position position = new Position();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, key);
            ResultSet rs = statement.executeQuery();
            rs.next();
            position.setId(rs.getInt("id"));
            position.setName(rs.getString("name"));
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return position;
    }

    @Override
    public User readUser(int key) throws PersistException {
        String sql = "SELECT * FROM users WHERE id = ?;";
        User user = new User();
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, key);
            ResultSet rs = stm.executeQuery();
            rs.next();
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setLogin(rs.getString("login"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            //user.setRole(readRole(rs.getInt("roles_id")));
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return user;
    }

    public Role readRole(int key) throws PersistException {
        String sql = "SELECT roles.id, roles.name, permition.can_read, permition.can_write," +
                " permition.can_delete,permition.roles_id  FROM roles, permition" +
                " WHERE roles.id = permition.roles_id AND id = ?;";
        Role role = new Role();
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, key);
            ResultSet rs = stm.executeQuery();
            rs.next();
            role.setId(rs.getInt("id"));
            role.setName(rs.getString("name"));
            Permition permition = new Permition();
            permition.setCanRead(rs.getBoolean("can_read"));
            permition.setCanWrite(rs.getBoolean("can_write"));
            permition.setCanDelete(rs.getBoolean("can_delete"));
            role.setPermition(permition);
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return role;
    }

    @Override
    public TaskType readTaskType(int key) throws PersistException {
        String sql = "SELECT * FROM task_type WHERE id = ?;";
        TaskType taskType = new TaskType();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, key);
            ResultSet rs = statement.executeQuery();
            rs.next();
            taskType.setId(rs.getInt("id"));
            taskType.setName(rs.getString("name"));
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return taskType;
    }

    @Override
    public TaskStatus readTaskStatus(int key) throws PersistException {
        String sql = "SELECT * FROM task_status WHERE id = ?;";
        TaskStatus taskStatus = new TaskStatus();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, key);
            ResultSet rs = statement.executeQuery();
            rs.next();
            taskStatus.setId(rs.getInt("id"));
            taskStatus.setName(rs.getString("name"));
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return taskStatus;
    }

    @Override
    public List<TaskType> getAllTaskType() {
        log.info("Enter to getAllTaskType()");
        String sql = "SELECT * FROM task_type;";
        List<TaskType> taskTypeList = new ArrayList<TaskType>();
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            log.warn("Executing statement query");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                TaskType taskType = new TaskType();
                taskType.setId(rs.getInt("id"));
                taskType.setName(rs.getString("name"));
                taskTypeList.add(taskType);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return taskTypeList;
    }

    @Override
    public void insertUpdateExtendedTableRecords(Task entity) throws PersistException {
        extendedTablesDao.insertUpdateTableRecords(entity, entity.getComments(), true);
    }

    @Override
    public void deleteExtendedTableRecords(Task company) throws PersistException {
        extendedTablesDao.deleteTableRecords(company, company.getComments());
    }
}
