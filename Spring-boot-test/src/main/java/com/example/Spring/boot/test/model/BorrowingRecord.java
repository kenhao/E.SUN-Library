package com.example.Spring.boot.test.model;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "Borrowing_Record")
public class BorrowingRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int borrowingId;

    @ManyToOne
    @JoinColumn(name = "User_Id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "Inventory_Id", nullable = false)
    private Inventory inventory;

    @Column(nullable = false)
    private Timestamp borrowingTime;

    @Column
    private Timestamp returnTime;

    // Constructors
    public BorrowingRecord() {
    }

    public BorrowingRecord(User user, Inventory inventory) {
        this.user = user;
        this.inventory = inventory;
        this.borrowingTime = new Timestamp(System.currentTimeMillis());
        // returnTime is initially null, indicating the item has not been returned yet
    }

    // Getters and Setters
    public int getBorrowingId() {
        return borrowingId;
    }

    public void setBorrowingId(int borrowingId) {
        this.borrowingId = borrowingId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Timestamp getBorrowingTime() {
        return borrowingTime;
    }

    public void setBorrowingTime(Timestamp borrowingTime) {
        this.borrowingTime = borrowingTime;
    }

    public Timestamp getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(Timestamp returnTime) {
        this.returnTime = returnTime;
    }

    // toString method for debugging purposes
    @Override
    public String toString() {
        return "BorrowingRecord{" +
                "borrowingId=" + borrowingId +
                ", user=" + (user != null ? user.getUserName() : "null") +
                ", inventory=" + (inventory != null ? inventory.getInventoryId() : "null") +
                ", borrowingTime=" + borrowingTime +
                ", returnTime=" + returnTime +
                '}';
    }
}
