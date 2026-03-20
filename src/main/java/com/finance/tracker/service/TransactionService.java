package com.finance.tracker.service;

import com.finance.tracker.entity.Category;
import com.finance.tracker.entity.Transaction;
import com.finance.tracker.entity.User;
import com.finance.tracker.repository.CategoryRepository;
import com.finance.tracker.repository.TransactionRepository;
import com.finance.tracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;


    public Transaction createTransaction(Long userId, Long categoryId, double amount, String type){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found."));

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found."));

        Transaction transaction = new Transaction();

        transaction.setAmount(amount);
        transaction.setType(type);
        transaction.setUser(user);
        transaction.setCategory(category);
        transaction.setDate(LocalDate.now());

        return transactionRepository.save(transaction);
    }
}
