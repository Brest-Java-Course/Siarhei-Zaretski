package com.epam.brest.courses.dao;

import com.epam.brest.courses.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by sphincs on 20.10.14.
 */
public class UserDaoImpl implements UserDao {

    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void addUser(User user) {
        jdbcTemplate.update("insert into USER (userid,login,name) values (?,?,?)",
                user.getUserId(),user.getLogin(),user.getUserName());
    }

    @Override
    public List<User> getUsers() {
        return jdbcTemplate.query("select userid, login, name from USER", new UserMapper());
    }

    @Override
    public User getUserById(Long userId) {
        return jdbcTemplate.queryForObject("select userid, login, name from USER where userid = ?",
                new Object[]{userId}, new UserMapper());
    }

    @Override
    public User getUserByLogin(String login) {
        return jdbcTemplate.queryForObject("select userid, login, name from USER where login = ?",
                new Object[]{login}, new UserMapper());
    }

    @Override
    public void removeUser(Long userId) {
        jdbcTemplate.update("delete from USER where userid = ?", userId);
    }

    public class UserMapper implements RowMapper<User> {

        public User mapRow(ResultSet rs, int i) throws SQLException {
            User user = new User();
            user.setUserId(rs.getLong("userid"));
            user.setLogin(rs.getString("login"));
            user.setUserName(rs.getString("name"));
            return user;
        }

    }

}
