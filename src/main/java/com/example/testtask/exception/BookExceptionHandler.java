package com.example.testtask.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BookExceptionHandler {
    @ExceptionHandler(value = {BookNotFoundException.class})
    public BookException handleBookNotFoundException(BookNotFoundException bookNotFoundException) {
        return new BookException(
                bookNotFoundException.getMessage(),
                bookNotFoundException.getCause(),
                HttpStatus.NOT_FOUND
        );
    }
}
