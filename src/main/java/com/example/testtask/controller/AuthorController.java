package com.example.testtask.controller;

import com.example.testtask.dto.MostReadableAuthorDTO;
import com.example.testtask.model.Authors;
import com.example.testtask.service.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/authors")
public class AuthorController {
    AuthorService authorService;

    @GetMapping("{idAuthor}")
    public Authors getAuthor(@PathVariable Long idAuthor) {
        return authorService.findById(idAuthor);
    }

    @GetMapping
    public List<Authors> getAll() {
        return authorService.findAll();
    }

    @GetMapping("most_readable")
    public MostReadableAuthorDTO getMostReadableAuthor(@RequestParam LocalDate from, @RequestParam LocalDate to) {
        return authorService.getMostReadableAuthorByTimeRange(from, to);
    }

    @PostMapping
    public Long addAuthor(@RequestBody Authors author) {
        return authorService.save(author);
    }
}
