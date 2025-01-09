package com.snackbar.orderRefactory.infrastructure.controllers;

import java.math.BigDecimal;

public record OrderItemRequest(
        String productId,
        int quantity,
        BigDecimal price
) {
}
