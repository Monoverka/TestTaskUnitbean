package com.example.testtask.service;

import com.example.testtask.model.Books;

public interface BookService {
    Long save(Books book);
    Books findById(Long id);
}
