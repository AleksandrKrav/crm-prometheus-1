package com.becomejavasenior.impl;

import com.becomejavasenior.*;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class CompanyDaoImpl extends AbstractJDBCDao<Company> implements CompanyDao  {

    private ExtendedTablesDao extendedTablesDao = new ExtendedTablesDao(connection);

    public CompanyDaoImpl(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public String getSelectQuery() {
        return "SELECT id, name, address, web, email, owner_id FROM company ";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO company (name, address, web, email, owner_id) \n" +
                "VALUES (?, ?, ?, ?, ?) ;";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE company SET name=?, address=?, web=?, email=?, owner_id=? WHERE id=?;";
    }

    @Override
    public String getDeleteQuery() {
        return "UPDATE company SET is_deleted=true WHERE id=?;";
    }

    @Override
    protected List<Company> parseResultSet(ResultSet rs) throws PersistException {
        LinkedList<Company> result = new LinkedList<Company>();
        try {
            while (rs.next()) {
                Company company = new Company();
                company.setId(rs.getInt("id"));
                company.setName(rs.getString("name"));
                company.setAddress(rs.getString("address"));
                company.setWeb(rs.getString("web"));
                company.setEmail(rs.getString("email"));
                company.setOwner(getUser(rs.getInt("owner_id")));
                company.setPhones(extendedTablesDao.getPhones(company));
                company.setFiles(extendedTablesDao.getFiles(company));
                company.setComments(extendedTablesDao.getComments(company));

                result.add(company);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Company object) throws PersistException {
        try {
            statement.setString(1, object.getName());
            statement.setString(2, object.getAddress());
            statement.setString(3, object.getWeb());
            statement.setString(4, object.getEmail());
            statement.setInt(5, object.getOwner().getId());

        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Company object) throws PersistException {
        try {
            statement.setString(1, object.getName());
            statement.setString(2, object.getAddress());
            statement.setString(3, object.getWeb());
            statement.setString(4, object.getEmail());
            statement.setInt(5, object.getOwner().getId());
            statement.setInt(6, object.getId());
            extendedTablesDao.insertUpdateTableRecords(object, object.getPhones(), false);
            extendedTablesDao.insertUpdateTableRecords(object, object.getFiles(), false);
            extendedTablesDao.insertUpdateTableRecords(object, object.getComments(), false);
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }


    @Override
    public User getUser(int key) throws PersistException {
        String sql = "SELECT users.id as users_id , users.name as users_name, users.login as users_login, " +
                "users.password as users_password, users.email as users_email, users.roles_id as users_roles_id, " +
                "roles.name as roles_name, permition.can_read, permition.can_write, permition.can_delete" +
                " FROM users, roles, permition WHERE users.roles_id = roles.id AND is_deleted = false" +
                " AND roles.id = permition.roles_id AND users.id = ?;";
        User user = new User();
        try (PreparedStatement stm = connection.prepareStatement(sql)){
            stm.setInt(1, key);
            ResultSet rs = stm.executeQuery();
            rs.next();
            user.setId(rs.getInt("users_id"));
            user.setName(rs.getString("users_name"));
            user.setLogin(rs.getString("users_login"));
            user.setPassword(rs.getString("users_password"));
            user.setEmail(rs.getString("users_email"));
            Role role = new Role();
            role.setId(rs.getInt("users_roles_id"));
            role.setName(rs.getString("roles_name"));
            Permition permition = new Permition();
            permition.setCanRead(rs.getBoolean("can_read"));
            permition.setCanWrite(rs.getBoolean("can_write"));
            permition.setCanDelete(rs.getBoolean("can_delete"));
            role.setPermition(permition);
            user.setRole(role);
        }catch (Exception e){
            throw new PersistException(e);
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() throws PersistException {
        String sql = "SELECT users.id as users_id , users.name as users_name, users.login as users_login, " +
                "users.password as users_password, users.email as users_email, users.roles_id as users_roles_id, " +
                "roles.name as roles_name, permition.can_read, permition.can_write, permition.can_delete" +
                " FROM users, roles, permition WHERE users.roles_id = roles.id AND is_deleted = false" +
                " AND roles.id = permition.roles_id;";
        List<User> users = new ArrayList<>();
        try (PreparedStatement stm = connection.prepareStatement(sql)){
            ResultSet rs = stm.executeQuery();
            while (rs.next()){
                User user = new User();
                user.setId(rs.getInt("users_id"));
                user.setName(rs.getString("users_name"));
                user.setLogin(rs.getString("users_login"));
                user.setPassword(rs.getString("users_password"));
                user.setEmail(rs.getString("users_email"));
                Role role = new Role();
                role.setId(rs.getInt("users_roles_id"));
                role.setName(rs.getString("roles_name"));
                Permition permition = new Permition();
                permition.setCanRead(rs.getBoolean("can_read"));
                permition.setCanWrite(rs.getBoolean("can_write"));
                permition.setCanDelete(rs.getBoolean("can_delete"));
                role.setPermition(permition);
                user.setRole(role);
                users.add(user);
            }
        }catch (Exception e){
            throw new PersistException(e);
        }
        return users;
    }


    @Override
    public List<PhoneType> getAllPhoneType() throws PersistException {
        String sql = "SELECT phone_type.id, phone_type.name FROM phone_type;";
        List<PhoneType> phoneTypes = new LinkedList<PhoneType>();
        try (PreparedStatement stm = connection.prepareStatement(sql)){
            ResultSet rs = stm.executeQuery();
            while (rs.next()){
                PhoneType phoneType = new PhoneType();
                phoneType.setId(rs.getInt("id"));
                phoneType.setName(rs.getString("name"));
                phoneTypes.add(phoneType);
            }
        }catch (Exception e){
            throw new PersistException(e);
        }
        return phoneTypes;
    }

    @Override
    public void insertUpdateExtendedTableRecords(Company entity) throws PersistException {

        extendedTablesDao.insertUpdateTableRecords(entity, entity.getPhones(), true);
        extendedTablesDao.insertUpdateTableRecords(entity, entity.getFiles(), true);
        extendedTablesDao.insertUpdateTableRecords(entity, entity.getComments(), true);

    }

    @Override
    public void deleteExtendedTableRecords(Company company) throws PersistException {

        extendedTablesDao.deleteTableRecords(company, company.getPhones());
        extendedTablesDao.deleteTableRecords(company, company.getFiles());
        extendedTablesDao.deleteTableRecords(company, company.getComments());

    }

    @Override
    public List<Contact> getAllContacts() throws PersistException {
        String sql = "SELECT id, name FROM contact WHERE is_deleted = false;";
        List<Contact> contacts = new ArrayList<Contact>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    Contact contact = new Contact();
                    contact.setId(rs.getInt("id"));
                    contact.setName(rs.getString("name"));

                    contacts.add(contact);
                }
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return contacts;
    }

    @Override
    public List<Position> getAllPositions() throws PersistException {
        String sql = "SELECT * FROM positions";
        List<Position> positions = new LinkedList<Position>();
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    Position position = new Position();
                    position.setId(rs.getInt("id"));
                    position.setName(rs.getString("name"));
                    positions.add(position);
                }

            } catch (SQLException e) {

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return positions;

    }

}
