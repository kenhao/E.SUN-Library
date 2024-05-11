package com.example.Spring.boot.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

import com.example.Spring.boot.test.model.BorrowingRecord;
import com.example.Spring.boot.test.repository.BorrowingRecordRepository;
import com.example.Spring.boot.test.service.BorrowingRecordService;
import com.example.Spring.boot.test.service.InventoryService;

import java.sql.Timestamp;
import java.util.List;

@Controller
public class BookController {

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private BorrowingRecordService borrowingRecordService;

    @Autowired
    private BorrowingRecordRepository borrowingRecordRepository;

    @GetMapping("/book_inventory")
    public String bookInventory(Model model, HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/user_login"; // Redirect to login if not logged in
        }
        model.addAttribute("allInventories", inventoryService.getAllInventories());
        return "book_inventory";
    }

    @PostMapping("/borrow_book")
    public String borrowBook(@RequestParam int inventoryId, HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/user_login"; // Redirect to login if not logged in
        }

        inventoryService.updateInventoryStatus(inventoryId, "已借閱");
        borrowingRecordService.createBorrowingRecord(userId, inventoryId);

        return "redirect:/book_inventory";
    }

    @GetMapping("/book_borrowed")
    public String showBorrowedBooks(HttpSession session, Model model) {
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/user_login"; // Redirect to login if not logged in
        }
        List<BorrowingRecord> records = borrowingRecordService.getBorrowingRecordsByUserId(userId);
        model.addAttribute("allRecords", records);
        return "book_borrowed";

    }

    @PostMapping("/return_book")
    public String returnBook(@RequestParam int borrowingId, HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/user_login"; // Redirect to login if not logged in
        }

        BorrowingRecord borrowingRecord = borrowingRecordRepository.findById(borrowingId)
                .orElseThrow(() -> new RuntimeException("Borrowing record not found"));

        borrowingRecord.getInventory().setStatus("可借閱");
        borrowingRecord.setReturnTime(new Timestamp(System.currentTimeMillis()));
        borrowingRecordRepository.save(borrowingRecord);

        return "redirect:/book_borrowed";
    }
}
