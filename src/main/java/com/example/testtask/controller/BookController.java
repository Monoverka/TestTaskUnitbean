package com.example.testtask.controller;


import com.example.testtask.model.Books;
import com.example.testtask.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/books")
public class BookController {
    BookService bookService;

    @GetMapping("{bookId}")
    public Books getBook(@PathVariable Long bookId) {
        return bookService.findById(bookId);
    }

    @PostMapping
    public Long addBook(@RequestBody Books book) {
        return bookService.save(book);
    }
}
