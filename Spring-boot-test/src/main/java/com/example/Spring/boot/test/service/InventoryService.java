// InventoryService.java
package com.example.Spring.boot.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// import com.example.Spring.boot.test.model.BorrowingRecord;
import com.example.Spring.boot.test.model.Inventory;
import com.example.Spring.boot.test.repository.InventoryRepository;
// import com.example.Spring.boot.test.model.User;
import java.util.List;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    public List<Inventory> getAllInventories() {
        return inventoryRepository.findAll();
    }

    public void updateInventoryStatus(int inventoryId, String status) {
        Inventory inventory = inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new RuntimeException("Inventory not found"));
        inventory.setStatus(status);
        inventoryRepository.save(inventory);
    }

}
