package com.example.testtask.dto;

import lombok.Data;
import lombok.NonNull;

import java.time.LocalDate;

@Data
public class MostReadableAuthorDTO {
    @NonNull
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private String surname;
    @NonNull
    private LocalDate birthDate;
    @NonNull
    private Long numberOfTakingBooks;

}
