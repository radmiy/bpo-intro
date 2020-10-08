package com.intro.users.demo.dao;

import com.intro.users.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value = "SELECT u FROM User u WHERE u.name LIKE '%' || :keyword || '%'"
            + " OR u.title LIKE '%' || :keyword || '%'"
            + " OR u.secureNumber LIKE '%' || :keyword || '%'")
    List<User> search(@Param("keyword") String keyword);
    
    @Query(value = "SELECT u FROM User u WHERE u.secureNumber = :keyword")
    User getUserBySecureNumber(@Param("keyword") String keyword);
}
