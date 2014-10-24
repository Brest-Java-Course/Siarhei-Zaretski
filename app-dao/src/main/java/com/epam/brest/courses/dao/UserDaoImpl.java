package com.epam.brest.courses.dao;

import com.epam.brest.courses.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * Created by sphincs on 20.10.14.
 */
public class UserDaoImpl implements UserDao {


    public static final String SELECT_ALL_USERS_SQL = "select userid, login, name from USER";
    public static final String DELETE_USER_SQL = "delete from USER where userid = ?";

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedJdbcTemplate;

    private static final Logger LOGGER = LogManager.getLogger();

    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public void addUser(User user) {
        LOGGER.debug("addUser({})", user);
        jdbcTemplate.update("insert into USER (userid,login,name) values (?,?,?)",
                user.getUserId(),user.getLogin(),user.getUserName());
    }

    @Override
    public List<User> getUsers() {
        LOGGER.debug("getUsers");
        return jdbcTemplate.query(SELECT_ALL_USERS_SQL, new UserMapper());
    }

    @Override
    public User getUserById(Long userId) {
        LOGGER.debug("getUserById(userId={})", userId);
        return jdbcTemplate.queryForObject("select userid, login, name from USER where userid = ?",
                new Object[]{userId}, new UserMapper());
    }

    @Override
    public User getUserByLogin(String login) {
        LOGGER.debug("getUserByLogin(login={})", login);
        return jdbcTemplate.queryForObject("select userid, login, name from USER where login = ?",
                new Object[]{login}, new UserMapper());
    }

    @Override
    public void removeUser(Long userId) {
        jdbcTemplate.update(DELETE_USER_SQL, userId);
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
