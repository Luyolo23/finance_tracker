package com.finance.tracker.service;

import com.finance.tracker.dto.*;
import com.finance.tracker.entity.*;
import com.finance.tracker.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

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

    public List<TransactionResponseDTO> getUserTransactions(Long userId){
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found."));

        List<Transaction> transactions = transactionRepository.findByUser(user);

        return transactions.stream()
                .map( t -> new TransactionResponseDTO(
                        t.getId(),
                        t.getAmount(),
                        t.getType(),
                        t.getCategory().getName()
                        ))
                .toList();
    }

    public SummaryDTO getSummary(Long userId){
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found."));

        List<Transaction> transactions = transactionRepository.findByUser(user);

        double totalIncome = 0;
        double totalExpense = 0;

        for (Transaction t : transactions){
            if (t.getType().equalsIgnoreCase("INCOME")){
                totalIncome += t.getAmount();
            }else{
                totalExpense += t.getAmount();
            }
        }

        double balance = totalIncome - totalExpense;
        return new SummaryDTO(totalIncome, totalExpense, balance);
    }
}
