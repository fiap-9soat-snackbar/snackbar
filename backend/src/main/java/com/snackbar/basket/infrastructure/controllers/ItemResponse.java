package com.snackbar.basket.infrastructure.controllers;

import java.math.BigDecimal;

public record ItemResponse(
        String id,
        String name,
        Integer quantity,
        BigDecimal price,
        Integer cookingTime,
        String customization
) {}
