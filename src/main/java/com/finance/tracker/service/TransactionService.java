package com.finance.tracker.service;

import com.finance.tracker.dto.TransactionRequestDTO;
import com.finance.tracker.dto.TransactionResponseDTO;
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


    public TransactionResponseDTO createTransaction(TransactionRequestDTO dto){
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found."));

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found."));

        Transaction transaction = new Transaction();

        transaction.setAmount(dto.getAmount());
        transaction.setType(dto.getType());
        transaction.setUser(user);
        transaction.setCategory(category);
        transaction.setDate(LocalDate.now());

        Transaction saved = transactionRepository.save(transaction);
        return new TransactionResponseDTO(
                saved.getId(),
                saved.getAmount(),
                saved.getType(),
                saved.getCategory().getName()
        );
    }
}
