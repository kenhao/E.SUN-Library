package com.example.Spring.boot.test.repository;

import com.example.Spring.boot.test.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByPhoneNumber(String phoneNumber);

}
