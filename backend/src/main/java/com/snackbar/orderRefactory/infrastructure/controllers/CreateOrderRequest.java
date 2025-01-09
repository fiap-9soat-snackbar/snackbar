package com.snackbar.orderRefactory.infrastructure.controllers;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public record CreateOrderRequest(
        String customerId,
        Instant orderDate,
        List<OrderItemRequest> items,
        BigDecimal totalPrice
) {
}

