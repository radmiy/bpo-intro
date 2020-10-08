package com.intro.users.demo.dao.impl;

import com.intro.users.demo.dao.UserDAO;
import com.intro.users.demo.dao.UserRepository;
import com.intro.users.demo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

import static java.util.Objects.nonNull;

@Repository
@Transactional
public class UserDAOImpl implements UserDAO {

    @Autowired
    private UserRepository userRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createUser(User user) {
        userRepository.save(user);
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
