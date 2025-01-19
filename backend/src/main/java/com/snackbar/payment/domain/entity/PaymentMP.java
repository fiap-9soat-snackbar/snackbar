package com.snackbar.payment.domain.entity;

import java.math.BigDecimal;

public record PaymentMP (String id, String paymentId, BigDecimal totalDue, String cpf, String callbackURL){
    
}