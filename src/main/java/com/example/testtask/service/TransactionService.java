package com.example.testtask.service;

import com.example.testtask.dto.TransactionDTO;
import com.example.testtask.model.Transactions;

import java.util.List;


public interface TransactionService {
    Long save(Transactions transaction);
    TransactionDTO findById(Long id);
    List<TransactionDTO> findAll();
}
