package com.example.testtask.service;

import com.example.testtask.dto.TransactionDTO;
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
import java.util.ArrayList;
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

        transaction.setId(null);
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
    public TransactionDTO findById(Long id) {
        Transactions transaction = transactionRepo.findById(id).orElse(null);
        if (transaction == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No transaction found");
        }
        return new TransactionDTO(transaction);
    }

    @Override
    public List<TransactionDTO> findAll() {
        List<Transactions> allTransactions = transactionRepo.findAll();
        if (allTransactions.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No transactions found");
        }
        List<TransactionDTO> transactionDTOS = new ArrayList<>();
        allTransactions.forEach(transaction -> transactionDTOS.add(new TransactionDTO(transaction)));
        return transactionDTOS;
    }
}
