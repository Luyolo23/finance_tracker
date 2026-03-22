package com.finance.tracker.controller;

import com.finance.tracker.dto.*;
import com.finance.tracker.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<TransactionResponseDTO> createTraction(@RequestBody TransactionRequestDTO dto){
        return ResponseEntity.ok(transactionService.createTransaction(dto));

    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TransactionResponseDTO>> getUserTransactions(@PathVariable Long userId){
        return ResponseEntity.ok(transactionService.getUserTransactions(userId));
    }

    @GetMapping("/summary/{userId}")
    public ResponseEntity<SummaryDTO> getSummary(@PathVariable Long userId){
        return ResponseEntity.ok(transactionService.getSummary(userId));
    }
}
