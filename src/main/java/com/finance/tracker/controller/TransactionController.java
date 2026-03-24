package com.finance.tracker.controller;

import com.finance.tracker.dto.*;
import com.finance.tracker.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<TransactionResponseDTO> createTraction(@RequestBody TransactionRequestDTO dto,
                                                                 Authentication authentication){
        String username = authentication.getName();
        return ResponseEntity.ok(transactionService.createTransaction(dto, username));

    }

    @GetMapping
    public ResponseEntity<Page<TransactionResponseDTO>> getUserTransactions(
            Authentication authentication,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam( required = false) String type,
            @RequestParam( required = false) String category
            ) {
        String username = authentication.getName();
        return ResponseEntity.ok(
                transactionService.getTransactions(username, page, size, type, category)
        );
    }

    @GetMapping("/summary/{userId}")
    public ResponseEntity<SummaryDTO> getSummary(@PathVariable Long userId){
        return ResponseEntity.ok(transactionService.getSummary(userId));
    }
}
