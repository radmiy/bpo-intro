package com.intro.users.demo.dao.impl;

import com.intro.users.demo.dao.UserDAO;
import com.intro.users.demo.dao.UserRepository;
import com.intro.users.demo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class UserDAOImpl implements UserDAO {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    @Override
    public User getUserBySecureNumber(String keyword) {
        return userRepository.getUserBySecureNumber(keyword);
    }

    @Override
    public List<User> search(String keyword) {
        return userRepository.search(keyword);
    }

    @Override
    public User getUserById(Integer id) {
        return userRepository.findById(id).orElse(new User());
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }
}
