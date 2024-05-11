package com.example.Spring.boot.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Spring.boot.test.model.Inventory;
import com.example.Spring.boot.test.model.User;
import com.example.Spring.boot.test.model.BorrowingRecord;
import com.example.Spring.boot.test.repository.BorrowingRecordRepository;
import com.example.Spring.boot.test.repository.InventoryRepository;
import com.example.Spring.boot.test.repository.UserRepository;

import java.sql.Timestamp;

import java.util.List;

@Service
public class BorrowingRecordService {

    @Autowired
    private BorrowingRecordRepository borrowingRecordRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    public void createBorrowingRecord(int userId, int inventoryId) {
        BorrowingRecord record = new BorrowingRecord();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        record.setUser(user);
        Inventory inventory = inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new RuntimeException("Inventory not found"));
        record.setInventory(inventory);
        record.setBorrowingTime(new Timestamp(System.currentTimeMillis())); // 使用當前時間作為借閱時間
        // Return time will be set when the book is returned
        borrowingRecordRepository.save(record);
    }

    public List<BorrowingRecord> getBorrowingRecordsByUserId(int userId) {
        return borrowingRecordRepository.findByUser_userId(userId);
    }

    public void updateBorrowingRecord(int userId, int inventoryId) {
        BorrowingRecord record = borrowingRecordRepository.findByUser_userIdAndInventory_inventoryId(userId,
                inventoryId);
        if (record != null) {
            record.setReturnTime(new Timestamp(System.currentTimeMillis()));
            borrowingRecordRepository.save(record); // 更新借閱紀錄 in database
        }
    }
}
