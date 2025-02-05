package com.example.testtask.service;

import com.example.testtask.dto.MostReadableAuthorDTO;
import com.example.testtask.model.Authors;
import com.example.testtask.model.TransactionType;
import com.example.testtask.repo.AuthorRepo;
import com.example.testtask.repo.TransactionRepo;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    AuthorRepo authorRepo;
    TransactionRepo transactionRepo;
    @Override
    public Long save(Authors author) {
        return authorRepo.save(author).getId();
    }

    @Override
    public Authors findById(Long id) {
        return authorRepo.findById(id).orElse(null);
    }

    @Override
    public List<Authors> findAll() {
        var authors = authorRepo.findAll();
        if (authors.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Authors not found");
        }
        return authorRepo.findAll();
    }

    @Override
    public MostReadableAuthorDTO getMostReadableAuthorByTimeRange(LocalDate from, LocalDate to) {
        var transactions = transactionRepo.findAll();
        var mapBookAuthorsAndNumberTakings = transactions.stream()
                .filter(t -> t.getDateTime().toLocalDate().isAfter(from)
                        || t.getDateTime().toLocalDate().isEqual(from)
                )
                .filter(t -> t.getDateTime().toLocalDate().isBefore(to)
                        || t.getDateTime().toLocalDate().isEqual(to)
                        )
                .filter(t -> t.getType() == TransactionType.TAKING)
                .collect(
                        Collectors.groupingBy(
                        t -> t.getBook().getAuthors(),
                                Collectors.counting()
                        )
                );
        Map<Authors, Long> AuthorAndNumberTakings = authorRepo.findAll()
                .stream()
                .collect(Collectors.toMap(i -> i, i -> 0L));

        for (Map.Entry<Authors, Long> entry : AuthorAndNumberTakings.entrySet()) {
            for (Map.Entry<List<Authors>, Long> entry2 : mapBookAuthorsAndNumberTakings.entrySet()) {
                if (entry2.getKey().contains(entry.getKey())) {
                    entry.setValue(entry.getValue() + entry2.getValue());
                }
            }
        }
        Long maxNumberTakings = AuthorAndNumberTakings.values().stream().max(Long::compareTo).orElse(null);
        for (Map.Entry<Authors, Long> entry : AuthorAndNumberTakings.entrySet()) {
            if (entry.getValue().equals(maxNumberTakings)) {
                var author = entry.getKey();
                var numberTakings = entry.getValue();

                return new MostReadableAuthorDTO(
                        author.getId(),
                        author.getName(),
                        author.getSurname(),
                        author.getBirthDate(),
                        numberTakings
                );
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Author not found");
    }
}
