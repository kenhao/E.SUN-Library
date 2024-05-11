package com.example.Spring.boot.test.model;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "Inventory")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int inventoryId;

    @ManyToOne
    @JoinColumn(name = "ISBN", nullable = false)
    private Book book;

    @Column(nullable = false)
    private Timestamp storeTime;

    @Column(length = 255, nullable = false)
    private String status;

    // getters and setters
    public int getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Timestamp getStoreTime() {
        return storeTime;
    }

    public void setStoreTime(Timestamp storeTime) {
        this.storeTime = storeTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
