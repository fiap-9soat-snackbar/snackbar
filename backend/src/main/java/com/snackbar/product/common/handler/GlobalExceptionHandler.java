package com.snackbar.product.common.handler;

import com.snackbar.product.common.dto.ApiResponse;
import com.snackbar.product.common.exception.BusinessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.stereotype.Component;

@ControllerAdvice("productGlobalExceptionHandler")
@Component("productGlobalExceptionHandler")
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResponse<?>> handleBusinessException(BusinessException e) {
        return ResponseEntity.badRequest().body(new ApiResponse<>(false, e.getMessage(), null));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleGenericException(Exception e) {
        return ResponseEntity.badRequest().body(new ApiResponse<>(false, "An unexpected error occurred", null));
    }
}