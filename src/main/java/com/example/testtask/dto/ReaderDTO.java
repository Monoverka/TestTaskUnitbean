package com.example.testtask.dto;

import com.example.testtask.model.Gender;
import com.example.testtask.model.Readers;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDate;

@Data
public class ReaderDTO {
    @NonNull
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private String surname;
    @NonNull
    private LocalDate birthDate;
    @NonNull
    private String phoneNumber;
    @NonNull
    private Gender gender;
    public ReaderDTO(Readers reader) {
        id = reader.getId();
        name = reader.getName();
        surname = reader.getSurname();
        birthDate = reader.getBirthDate();
        phoneNumber = reader.getPhoneNumber();
        gender = reader.getGender();
    }
}
