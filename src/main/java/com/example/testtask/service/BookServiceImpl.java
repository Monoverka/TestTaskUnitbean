package com.example.testtask.service;

import com.example.testtask.exception.BookNotFoundException;
import com.example.testtask.model.Books;
import com.example.testtask.repo.BookRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
    BookRepo bookRepo;

    @Override
    public Long save(Books book) {
        return bookRepo.save(book).getId();
    }

    @Override
    public Books findById(Long id) {
        var result = bookRepo.findById(id);
        if (result.isEmpty()) {
            throw new BookNotFoundException("Book does not exist");
        }
        return result.get();
    }
}
