package com.snackbar.payment.infrastructure.controllers;

public record CreatePaymentRequest (String orderId, String paymentMethod) {
    
}