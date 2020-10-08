package com.intro.users.demo.facades;

import com.intro.users.demo.models.User;

import java.util.List;

public interface UserFacade {
    void createUser(User user);

    void deleteUser(Integer id);

    User getUserById(Integer id);

    User getUserBySecureNumber(String keyword);
    
    List<User> search(String keyword);

    List<User> getAllUser();
}
