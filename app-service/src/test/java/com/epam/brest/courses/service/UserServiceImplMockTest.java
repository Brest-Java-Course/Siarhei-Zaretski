package com.epam.brest.courses.service;

import com.epam.brest.courses.dao.UserDao;
import com.epam.brest.courses.domain.User;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

import javax.swing.*;
import java.util.zip.DataFormatException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/spring-services-mock-test.xml"})
public class UserServiceImplMockTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDao userDao;

    @After
    public void clean() {
        reset(userDao);
    }

    @Test
    public void addUser() {
        User user = UserDataFixture.getNewUser();
        expect(userDao.addUser(user)).andReturn(Long.valueOf(1L));
        expect(userDao.getUserByLogin(user.getLogin())).andReturn(null);
        replay(userDao);
        Long id = userService.addUser(user);
        assertEquals(id, Long.valueOf(1L));
    }

    @Test
    public void getUserByLogin() {
        User user = UserDataFixture.getExistUser(1L);
        expect(userDao.getUserByLogin(user.getLogin())).andReturn(user);
        replay(userDao);
        User result = userService.getUserByLogin(user.getLogin());
        verify(userDao);
        assertSame(user, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addUserWithSameLogin() {
        User user = UserDataFixture.getNewUser();
        expect(userDao.getUserByLogin(user.getLogin())).andReturn(user);
        replay(userDao);
        userService.addUser(user);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void throwException() {
        User user = UserDataFixture.getNewUser();
        expect(userDao.getUserByLogin(user.getLogin())).andThrow(new UnsupportedOperationException());
        replay(userDao);
        userService.addUser(user);
    }
}