package com.epam.brest.courses.service;

import com.epam.brest.courses.domain.User;
import java.util.List;

/**
 * Created by sphincs on 20.10.14.
 */

public interface UserService {

    public void addUser(User user);

    public User getUserByLogin(String login);

}
