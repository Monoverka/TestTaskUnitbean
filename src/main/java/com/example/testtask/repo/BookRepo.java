package com.example.testtask.repo;

import com.example.testtask.model.Books;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookRepo extends JpaRepository<Books, Long> {
}
