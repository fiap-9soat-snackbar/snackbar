package com.snackbar.orderRefactory.domain.entity;

import com.snackbar.orderRefactory.domain.valueobject.StatusOrder;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public record Order(
        String id,
        String orderNumber,
        Instant orderDateTime,
        String cpf,
        String name,
        List<OrderItem> items,
        StatusOrder statusOrder,
        String paymentMethod,
        BigDecimal totalPrice,
        long remainingTime
) {
    public long calculateRemainingTime() {
        Instant now = Instant.now();
        long elapsedTime = java.time.Duration.between(orderDateTime, now).toMinutes();
        return Math.max(0, getWaitingTime() - elapsedTime);
    }

    public int getWaitingTime() {
        return items.stream()
                .mapToInt(item -> item.cookingTime() * item.quantity())
                .sum();
    }

    public static String generateOrderNumber(String lastOrderNumber) {
        if (lastOrderNumber == null || lastOrderNumber.isEmpty()) {
            return "000001";
        }
        int nextNumber = Integer.parseInt(lastOrderNumber) + 1;
        return String.format("%06d", nextNumber);
    }
}
