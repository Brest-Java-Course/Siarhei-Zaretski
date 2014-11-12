package com.epam.brest.courses.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class UserTest {

    private static final Long USER_ID = 1L;
    private static final String USER_LOGIN = "UserLogin";
    private static final String USER_NAME = "UserName";


    User user;

    @Before
    public void setUp() throws Exception {
        user = new User();
    }

    @Test
    public void testCreateUserWithSameParameters() throws Exception{
        User user = new User(USER_ID, USER_LOGIN, USER_NAME);
        assertEquals(USER_ID, user.getUserId());
        assertEquals(USER_LOGIN, user.getLogin());
        assertEquals(USER_NAME, user.getName());
    }

    @Test
    public void testGetName() throws Exception {
        user.setName(USER_NAME);
        assertEquals(USER_NAME, user.getName());
    }

    @Test
    public void testGetLogin() throws Exception {
        user.setLogin(USER_LOGIN);
        assertEquals(USER_LOGIN, user.getLogin());
    }

    @Test
    public void testGetUserId() throws Exception {
        user.setUserId(USER_ID);
        assertEquals(USER_ID, user.getUserId());
    }
}
