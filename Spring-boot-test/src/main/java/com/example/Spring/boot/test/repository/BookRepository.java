package com.example.Spring.boot.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Spring.boot.test.model.Book;

public interface BookRepository extends JpaRepository<Book, String> {
    // Custom database queries can be added here
}
