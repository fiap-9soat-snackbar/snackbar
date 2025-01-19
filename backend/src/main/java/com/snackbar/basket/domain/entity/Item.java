package com.snackbar.basket.domain.entity;

import java.math.BigDecimal;

public record Item(
        String id,
        String name,
        Integer quantity,
        BigDecimal price,
        Integer cookingTime,
        String customization
) {}
