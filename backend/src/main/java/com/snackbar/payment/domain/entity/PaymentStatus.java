package com.snackbar.payment.domain.entity;

public record PaymentStatus(String externalId, String paymentStatus, String callbackURL) {
}
