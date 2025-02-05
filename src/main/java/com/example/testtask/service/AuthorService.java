package com.example.testtask.service;

import com.example.testtask.dto.MostReadableAuthorDTO;
import com.example.testtask.model.Authors;

import java.time.LocalDate;
import java.util.List;

public interface AuthorService {
    Long save(Authors author);
    Authors findById(Long id);
    List<Authors> findAll();
    MostReadableAuthorDTO getMostReadableAuthorByTimeRange(LocalDate from, LocalDate to);
}
