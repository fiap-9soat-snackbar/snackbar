package com.snackbar.payment.domain.entities;

// This should be a pure Payment business domain entity, not a JPA/database entity

import java.math.BigDecimal;

public record Payment(String id, String orderId, BigDecimal totalDue, String paymentStatus, String paymentMethod, String externalPaymentId) {

}


