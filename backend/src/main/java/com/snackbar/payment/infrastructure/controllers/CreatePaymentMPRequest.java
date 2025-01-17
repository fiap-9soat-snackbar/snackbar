package com.snackbar.payment.infrastructure.controllers;

import java.math.BigDecimal;

public record CreatePaymentMPRequest (String paymentId, BigDecimal totalDue, String cpf, String callbackURL) {
    
}