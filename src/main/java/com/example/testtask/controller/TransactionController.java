package com.example.testtask.controller;

import com.example.testtask.dto.TransactionDTO;
import com.example.testtask.model.Transactions;
import com.example.testtask.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    TransactionService transactionService;

    @GetMapping
    public List<TransactionDTO> getAll() {
        return transactionService.findAll();
    }

    @GetMapping("{idTransaction}")
    public TransactionDTO getById(@PathVariable Long idTransaction) {
        return transactionService.findById(idTransaction);
    }

    @PostMapping
    public Long addTransaction(@RequestBody Transactions transaction) {
        return transactionService.save(transaction);
    }
}
