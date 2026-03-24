package com.finance.tracker.repository;

import com.finance.tracker.entity.Transaction;
import com.finance.tracker.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByUser(User user);
    Page<Transaction> findByUser(User user, Pageable pageable);
    Page<Transaction> findByUserAndTypeIgnoreCase(User user, String type, Pageable pageable);
    Page<Transaction> findByUserAndCategory_NameIgnoreCase(User user, String categoryName, Pageable pageable);
    Page<Transaction> findByUserAndTypeIgnoreCaseAndCategory_NameIgnoreCase(
            User user,
            String type,
            String categoryName,
            Pageable pageable);
}
