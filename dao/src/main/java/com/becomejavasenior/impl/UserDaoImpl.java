package com.becomejavasenior.impl;


import com.becomejavasenior.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class UserDaoImpl extends AbstractJDBCDao<User> implements UserDao {

    public UserDaoImpl(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public String getSelectQuery() {
        return "SELECT * FROM users ";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO users (name, login, password, email, roles_id) \n" +
                "VALUES (?, ?, ?, ?, ? );";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE users SET name=?, login=?, password=?, email=?, roles_id=? WHERE id=?;";
    }

    @Override
    public String getDeleteQuery() {
        return "UPDATE users SET is_deleted=TRUE WHERE id=?;";
    }


    @Override
    protected List<User> parseResultSet(ResultSet rs) throws PersistException {
        LinkedList<User> result = new LinkedList<User>();
        try {
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setRole(readRole(rs.getInt("roles_id")));
                result.add(user);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, User object) throws PersistException {
        try {
            statement.setString(1, object.getName());
            statement.setString(2, object.getLogin());
            statement.setString(3, object.getPassword());
            statement.setString(4, object.getEmail());
            statement.setInt(5, object.getRole().getId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, User object) throws PersistException {
        try {
            statement.setString(1, object.getName());
            statement.setString(2, object.getLogin());
            statement.setString(3, object.getPassword());
            statement.setString(4, object.getEmail());
            statement.setInt(5, object.getRole().getId());
            statement.setInt(6, object.getId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    public Role readRole(int key) throws PersistException {
        String sql = "SELECT roles.id, roles.name, permition.can_read, permition.can_write," +
                " permition.can_delete,permition.roles_id  FROM roles, permition" +
                " WHERE roles.id = permition.roles_id AND id = ?;";
        Role role = new Role();
        try (PreparedStatement stm = connection.prepareStatement(sql)){
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
        }catch (Exception e){
            throw new PersistException(e);
        }
        return role;
    }

    @Override
    public User getUserByEmail(String userEmail) throws PersistException {
        String sql = "SELECT * FROM users WHERE email = ?;";
        User user = null;
        try(PreparedStatement stm = connection.prepareStatement(sql)){
            stm.setString(1, userEmail);
            ResultSet rs = stm.executeQuery();
            rs.next();
            user = new User();
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setLogin(rs.getString("login"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setRole(readRole(rs.getInt("roles_id")));
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return user;
    }
}