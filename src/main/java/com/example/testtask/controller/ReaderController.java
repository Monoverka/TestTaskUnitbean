package com.example.testtask.controller;

import com.example.testtask.dto.ReaderDTO;
import com.example.testtask.model.Readers;
import com.example.testtask.service.ReaderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/api/readers")
public class ReaderController {
    ReaderService readerService;
    @GetMapping("{idReader}")
    public Readers getReader(@PathVariable Long idReader) {
        return readerService.findById(idReader);
    }

    @GetMapping("mostReadingReader")
    public ReaderDTO getMostReadingReader() {
        return readerService.findMostReadingReader();
    }

    @GetMapping("allDebtors")
    public List<ReaderDTO> getAllDebtors() {
        return readerService.findAllDebtors();
    }

    @PostMapping
    public Long addReader(@RequestBody Readers reader) {
        return readerService.save(reader);
    }
}
