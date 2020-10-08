package com.intro.users.demo.dao;

import com.intro.users.demo.models.User;

import java.util.List;

public interface UserDAO {
    User createUser(User user);

    void deleteUser(User user);

    User getUserById(Integer id);

    User getUserBySecureNumber(String keyword);
    
    List<User> search(String keyword);

    List<User> getAllUser();
}
