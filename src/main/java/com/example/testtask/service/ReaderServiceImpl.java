package com.example.testtask.service;

import com.example.testtask.dto.ReaderDTO;
import com.example.testtask.model.Readers;
import com.example.testtask.repo.ReaderRepo;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ReaderServiceImpl implements ReaderService {
    ReaderRepo readerRepo;
    @Override
    public Long save(Readers reader) {
        return readerRepo.save(reader).getId();
    }

    @Override
    public Readers findById(Long id) {
        return readerRepo.findById(id).orElse(null);
    }

    @Override
    public ReaderDTO findMostReadingReader() {
        Readers reader = readerRepo.findMostReadingReader();
        if (reader == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No reader found");
        }
        return new ReaderDTO(reader);
    }

    @Override
    public List<ReaderDTO> findAllDebtors() {
        List<Readers> readers = readerRepo.findAllDebtors();
        if (readers == null || readers.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No debtor found");
        }
        List<ReaderDTO> readerDTOS = new ArrayList<>();
        for (Readers reader : readers) {
            readerDTOS.add(new ReaderDTO(reader));
        }
        return readerDTOS;
    }
}
