package com.snackbar.product.common.controller;

import com.snackbar.product.common.dto.ApiResponse;
import org.springframework.http.ResponseEntity;

import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.badRequest;

public abstract class BaseController {
    
    protected <T> ResponseEntity<ApiResponse<T>> success(T data, String message) {
        return ok(new ApiResponse<>(true, message, data));
    }

    protected <T> ResponseEntity<ApiResponse<T>> success(T data) {
        return success(data, "Operation successful");
    }

    protected <T> ResponseEntity<ApiResponse<T>> error(String message) {
        return badRequest().body(new ApiResponse<>(false, message, null));
    }
}