package com.finance.tracker.controller;

import com.finance.tracker.entity.Transaction;
import com.finance.tracker.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Transaction> createTraction(
            @RequestParam Long userId,
            @RequestBody Long categoryId,
            @RequestParam double amount,
            @RequestParam String type
    ){
        Transaction transaction = transactionService.createTransaction(userId, categoryId, amount, type);

        return ResponseEntity.ok(transaction);

    }
}
