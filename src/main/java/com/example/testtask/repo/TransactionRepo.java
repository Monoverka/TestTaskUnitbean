package com.example.testtask.repo;

import com.example.testtask.model.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TransactionRepo extends JpaRepository<Transactions, Long> {
    @Query("select t from Transactions t where t.book.id = ?1 order by t.dateTime desc limit 1")
    Transactions findLastByBookId(Long bookId);
}
