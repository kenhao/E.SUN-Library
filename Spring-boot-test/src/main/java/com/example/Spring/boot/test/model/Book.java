package com.example.Spring.boot.test.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Book")
public class Book {

    @Id
    @Column(length = 255, nullable = false)
    private String isbn;

    @OneToMany(mappedBy = "book", fetch = FetchType.EAGER)
    private List<Inventory> inventories;

    @Column(length = 255, nullable = false)
    private String name;

    @Column(length = 255, nullable = false)
    private String author;

    @Column(columnDefinition = "TEXT")
    private String introduction;

    // Getters and Setters
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    // toString method for debugging purposes
    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", introduction='" + introduction + '\'' +
                '}';
    }
}
