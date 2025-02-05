package com.example.testtask.repo;

import com.example.testtask.model.Authors;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepo extends JpaRepository<Authors, Long> {
}
