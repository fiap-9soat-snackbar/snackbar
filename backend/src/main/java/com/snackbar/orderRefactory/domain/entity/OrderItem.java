package com.snackbar.orderRefactory.domain.entity;

import java.math.BigDecimal;

public record OrderItem(
        String id,
        String name,
        Integer quantity,
        BigDecimal price,
        Integer cookingTime,
        String customization
) {}
