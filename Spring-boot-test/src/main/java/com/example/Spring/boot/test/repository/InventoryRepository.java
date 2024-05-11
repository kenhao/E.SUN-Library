package com.example.Spring.boot.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Spring.boot.test.model.Inventory;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
    List<Inventory> findAllByOrderByBookNameAsc();

}
