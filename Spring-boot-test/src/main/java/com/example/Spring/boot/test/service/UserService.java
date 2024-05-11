package com.example.Spring.boot.test.service;

import com.example.Spring.boot.test.model.User;
import com.example.Spring.boot.test.repository.UserRepository;

import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public String hashPassword(String password) {
        try {
            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[16];
            random.nextBytes(salt);
            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

            byte[] hash = factory.generateSecret(spec).getEncoded();
            return Base64.getEncoder().encodeToString(salt) + "$" + Base64.getEncoder().encodeToString(hash);
        } catch (Exception e) {
            throw new RuntimeException("Error while hashing a password: " + e.getMessage(), e);
        }
    }

    public void registerUser(User user) {
        user.setPassword(hashPassword(user.getPassword()));
        userRepository.save(user);
    }

    public boolean isPhoneNumberExists(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber) != null;
    }

    // Authenticate the user by checking if the phone number and password match and
    // return the user object
    public User authenticateUser(String phoneNumber, String password) {
        User user = userRepository.findByPhoneNumber(phoneNumber);
        if (user != null && checkPassword(password, user.getPassword())) {
            // Update the last login time
            user.setLastLoginTime(new Timestamp(System.currentTimeMillis()));
            userRepository.save(user);
            return user;
        } else {
            return null;
        }
    }

    private boolean checkPassword(String password, String storedPassword) {
        try {
            String[] parts = storedPassword.split("\\$");
            if (parts.length != 2) {
                throw new IllegalArgumentException("Stored password must have exactly one $ separator.");
            }
            byte[] salt = Base64.getDecoder().decode(parts[0]);
            byte[] hash = Base64.getDecoder().decode(parts[1]);

            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

            byte[] testHash = factory.generateSecret(spec).getEncoded();
            return Arrays.equals(hash, testHash);
        } catch (Exception e) {
            throw new RuntimeException("Error while checking a password: " + e.getMessage(), e);
        }
    }

}