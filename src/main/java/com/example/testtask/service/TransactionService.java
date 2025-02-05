package com.example.testtask.service;

import com.example.testtask.model.Transactions;

import java.util.List;


public interface TransactionService {
    Long save(Transactions transaction);
    Transactions findById(Long id);
    List<Transactions> findAll();
}
