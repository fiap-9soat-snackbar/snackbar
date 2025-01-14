package com.snackbar.orderRefactory.infrastructure.controllers;

import java.math.BigDecimal;

public record OrderItemResponse(
        String productId,
        int quantity,
        BigDecimal price
) {
}
