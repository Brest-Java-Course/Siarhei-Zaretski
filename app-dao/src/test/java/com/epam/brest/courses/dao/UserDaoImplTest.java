package com.epam.brest.courses.dao;

import com.epam.brest.courses.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;


/**
 * Created by sphincs on 22.10.14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/testApplicationContextSpring.xml"})
public class UserDaoImplTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void getUsers() {
        List<User> users = userDao.getUsers();
        assertNotNull(users);
        assertFalse(users.isEmpty());
    }

    @Test
    public void addUser() {
        List<User> users = userDao.getUsers();
        int sizeBefore = users.size();

        User user = new User();
        user.setUserId(3L);
        user.setLogin("userLogin3");
        user.setUserName("userName3");
        userDao.addUser(user);
        users = userDao.getUsers();
        assertEquals(sizeBefore+1, users.size());
    }

    @Test
    public void removeUser() {
        List<User> users = userDao.getUsers();
        int sizeBefore = users.size();
        userDao.removeUser(1L);
        users = userDao.getUsers();
        assertEquals(sizeBefore-1, users.size());
    }

    @Test
    public void getUserByLogin() {
        User user = userDao.getUserByLogin("userLogin1");
        assertNotNull(user);
        assertTrue(user.getLogin().equals("userLogin1"));
    }

    @Test
    public void getUserById() {
        User user = userDao.getUserById(3L);
        assertNotNull(user);
        assertTrue(user.getUserId() == 3L);
    }


}
