package com.intro.users.demo.facades.impl;

import com.intro.users.demo.dao.UserDAO;
import com.intro.users.demo.facades.UserFacade;
import com.intro.users.demo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Objects.nonNull;

@Component
public class UserFacadeImpl implements UserFacade {
    @Autowired
    private UserDAO userDAO;
    
    @Override
    public void createUser(User user) {
        userDAO.createUser(user);
    }

    @Override
    public void deleteUser(Integer id) {
        if (nonNull(id)) {
            User user = userDAO.getUserById(id);
            userDAO.deleteUser(user);
        }
    }

    @Override
    public User getUserById(Integer id) {
        return userDAO.getUserById(id);
    }

    @Override
    public User getUserBySecureNumber(String keyword) {
        return userDAO.getUserBySecureNumber(keyword);
    }

    @Override
    public List<User> search(String keyword) {
        return userDAO.search(keyword);
    }

    @Override
    public List<User> getAllUser() {
        return userDAO.getAllUser();
    }
}
