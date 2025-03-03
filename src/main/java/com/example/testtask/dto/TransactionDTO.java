package com.example.testtask.dto;

import com.example.testtask.model.TransactionType;
import com.example.testtask.model.Transactions;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


import java.time.LocalDateTime;

@Data
public class TransactionDTO {
    @NotNull
    private Long id;
    @NotNull
    private TransactionType type;
    @NotNull
    private LocalDateTime dateTime;
    @NotNull
    private Long bookId;
    @NotNull
    private Long readerId;

    public TransactionDTO(Transactions transaction) {
        id = transaction.getId();
        type = transaction.getType();
        dateTime = transaction.getDateTime();
        bookId = transaction.getBook().getId();
        readerId = transaction.getReader().getId();
    }
}
