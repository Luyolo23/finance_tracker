package com.finance.tracker.repository;

import com.finance.tracker.entity.Transaction;
import com.finance.tracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByUser(User user);
}
