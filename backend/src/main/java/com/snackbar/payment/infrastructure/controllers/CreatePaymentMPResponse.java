package com.snackbar.payment.infrastructure.controllers;

import java.math.BigDecimal;

public record CreatePaymentMPResponse (String id, String paymentId, BigDecimal totalDue, String cpf, String callbackURL){
    
}
