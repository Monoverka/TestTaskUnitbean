package com.example.testtask.service;

import com.example.testtask.dto.ReaderDTO;
import com.example.testtask.model.Readers;

import java.util.List;

public interface ReaderService {
    Long save(Readers reader);
    Readers findById(Long id);
    ReaderDTO findMostReadingReader();
    List<ReaderDTO> findAllDebtors();
}
