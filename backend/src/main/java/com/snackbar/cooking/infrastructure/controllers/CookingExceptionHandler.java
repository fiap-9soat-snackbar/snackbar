package com.snackbar.cooking.infrastructure.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.snackbar.cooking.domain.exceptions.CookingNotFoundException;

@ControllerAdvice
public class CookingExceptionHandler {

    @ExceptionHandler(CookingNotFoundException.class)
    public ResponseEntity<String> handleCookingNotFoundException(CookingNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }
}