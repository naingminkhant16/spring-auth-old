package com.moe.exception_handling.demo.repository;

import com.moe.exception_handling.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT u FROM User u WHERE u.username = ?1 and password = ?2")
    User processLogin(String username, String password);
}
