package com.snackbar.cooking.infrastructure.handlers;

import com.snackbar.cooking.domain.exceptions.OrderStatusInvalidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CookingExceptionHandler {

    @ExceptionHandler(OrderStatusInvalidException.class)
    public ResponseEntity<ErrorResponse> handleOrderStatusInvalidException(OrderStatusInvalidException ex) {
        ErrorResponse error = new ErrorResponse(
            "INVALID_ORDER_STATUS",
            ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    private record ErrorResponse(String code, String message) {}
}