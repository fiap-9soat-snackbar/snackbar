package com.snackbar.productv2.infrastructure.controllers;

import java.math.BigDecimal;

public record CreateProductResponse (String name, String category, String description, BigDecimal price, Integer cookingTime) {
    
}
