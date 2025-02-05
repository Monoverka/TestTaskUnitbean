package com.example.testtask.service;

import com.example.testtask.exception.BookNotFoundException;
import com.example.testtask.model.TransactionType;
import com.example.testtask.model.Transactions;
import com.example.testtask.repo.BookRepo;
import com.example.testtask.repo.ReaderRepo;
import com.example.testtask.repo.TransactionRepo;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    TransactionRepo transactionRepo;
    BookRepo bookRepo;
    ReaderRepo readerRepo;

    @Override
    public Long save(Transactions transaction) {
        if (bookRepo.findById(transaction.getBook().getId()).isEmpty()) {
            throw new BookNotFoundException("Book does not exist");
        }
        if (readerRepo.findById(transaction.getReader().getId()).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reader does not exist");
        }

        //transaction.setId(null);
        transaction.setDateTime(LocalDateTime.now());

        Transactions lastTransaction = transactionRepo.findLastByBookId(transaction.getBook().getId());

        if (lastTransaction == null
                || lastTransaction.getType().equals(TransactionType.RETURNING)) {
            transaction.setType(TransactionType.TAKING);
            return transactionRepo.save(transaction).getId();
        }

        if (lastTransaction.getReader().getId().equals(transaction.getReader().getId())) {
            transaction.setType(TransactionType.RETURNING);
            return transactionRepo.save(transaction).getId();
        }

        throw new ResponseStatusException(
                HttpStatus.METHOD_NOT_ALLOWED,
                "Book already taking"
        );
    }

    @Override
    public Transactions findById(Long id) {
        return transactionRepo.findById(id).orElse(null);
    }

    @Override
    public List<Transactions> findAll() {
        List<Transactions> allTransactions = transactionRepo.findAll();
        if (allTransactions.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No transactions found");
        }
        return transactionRepo.findAll();
    }
}
