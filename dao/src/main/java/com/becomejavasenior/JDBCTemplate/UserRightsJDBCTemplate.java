package com.becomejavasenior.JDBCTemplate;

import com.becomejavasenior.Permition;
import com.becomejavasenior.Role;
import com.becomejavasenior.User;
import com.becomejavasenior.UserRightsDao;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserRightsJDBCTemplate extends JdbcDaoSupport implements UserRightsDao {

    private RowMapper<User> userMapper = new RowMapper<User>() {
        @Override
        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setName(resultSet.getString("name"));
            user.setLogin(resultSet.getString("login"));
            user.setPassword(resultSet.getString("password"));
            user.setEmail(resultSet.getString("email"));
            user.setRole(getRoleById(resultSet.getInt("roles_id")));
            return user;
        }
    };

    private RowMapper<Role> roleMapper = new RowMapper<Role>() {
        @Override
        public Role mapRow(ResultSet resultSet, int i) throws SQLException {
            Role role = new Role();
            role.setId(resultSet.getInt("id"));
            role.setName(resultSet.getString("name"));
            Permition permition = new Permition();
            permition.setCanRead(resultSet.getBoolean("can_read"));
            permition.setCanWrite(resultSet.getBoolean("can_write"));
            permition.setCanDelete(resultSet.getBoolean("can_delete"));
            role.setPermition(permition);
            return role;
        }
    };

    @Override
    public Role getRoleById(Integer id) {
        String sql = "SELECT roles.id, roles.name, permition.can_read, permition.can_write," +
                " permition.can_delete,permition.roles_id  FROM roles, permition " +
                " WHERE roles.id = permition.roles_id AND id = ?;";
        return getJdbcTemplate().queryForObject(sql, roleMapper, id);
    }

    @Override
    public List<User> getAll() {
        String sql = "SELECT * FROM users";
        return getJdbcTemplate().query(sql, userMapper);
    }

    @Override
    public void create(User user) {
        String sql = "INSERT INTO users (name, login, password, email, roles_id)" +
                "VALUES (?, ?, ?, ?, ? );";
        getJdbcTemplate().update(sql, getPreparedStatementSetter(user));
    }

    private PreparedStatementSetter getPreparedStatementSetter(final User user) {
        return new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                int i = 0;
                preparedStatement.setString(++i, user.getName());
                preparedStatement.setString(++i, user.getLogin());
                preparedStatement.setString(++i, user.getPassword());
                preparedStatement.setString(++i, user.getEmail());
                preparedStatement.setInt(++i, user.getRole().getId());
            }
        };
    }
}
