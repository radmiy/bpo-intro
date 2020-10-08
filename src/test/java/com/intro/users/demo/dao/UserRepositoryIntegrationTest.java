package com.intro.users.demo.dao;

import com.intro.users.demo.models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static java.util.Objects.nonNull;
import static org.springframework.test.util.AssertionErrors.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserRepositoryIntegrationTest {
    @Autowired
    private UserDAO userRepository;

    private User test;

    @BeforeEach
    void befor() {
        User user = userRepository.getUserBySecureNumber("test");
        if (nonNull(user)) {
            userRepository.deleteUser(user);
        }
        test = new User();
        test.setName("test");
        test.setTitle("test");
        test.setSecureNumber("test_SecureNumber");
        test = userRepository.createUser(test);
    }

    @AfterEach
    void after() {
        userRepository.deleteUser(test);
    }

    @Test
    void createUserTest() {
        User alex = new User();
        alex.setName("Alex");
        alex.setTitle("Title");
        alex.setSecureNumber("SecureNumber");
        alex = userRepository.createUser(alex);

        assertNotNull("Error. Must be ID", alex.getId());
        userRepository.deleteUser(alex);
    }

    @Test
    void updateUserTest() {
        test.setName("Alex");
        User updateUser = userRepository.createUser(test);

        assertNotNull("Error. Must be ID", test.getId());
        assertEquals("Not equals names", updateUser.getName(), test.getName());
    }

    @Test
    void readUserTest() {
        User found = userRepository.getUserById(test.getId());

        assertNotNull("Error. Must be ID", found.getId());
        assertEquals("Not equals ids", found.getId(), test.getId());
    }

    @Test
    void deleteUserTest() {
        userRepository.deleteUser(test);
        User found = userRepository.getUserById(test.getId());

        assertNull("Error. Must be null", found.getId());
    }

    @Test
    void searchUserTest() {
        List<User> found = userRepository.search("test_SecureNumber");

        assertNotNull("Error. Must be not null", found);
        assertEquals("Error. Must be only one", 1, found.size());
    }
}
