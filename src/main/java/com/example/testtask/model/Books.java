package com.example.testtask.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "books")
@Entity
public class Books {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "publishing_year", nullable = false)
    private Integer publishingYear;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "books_authors",
    joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "author_id", referencedColumnName = "id")
    )
    @JsonIgnoreProperties("books")
    private List<Authors> authors;
}
