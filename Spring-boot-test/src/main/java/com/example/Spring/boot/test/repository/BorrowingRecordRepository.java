package com.example.Spring.boot.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Spring.boot.test.model.BorrowingRecord;
// import com.example.Spring.boot.test.model.Inventory;

import java.util.List;

@Repository
public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord, Integer> {
    // 查找特定庫存的最後一筆未歸還的借閱紀錄
    List<BorrowingRecord> findByUser_userId(int userId);

    BorrowingRecord findByUser_userIdAndInventory_inventoryId(int userId, int inventoryId);
}
