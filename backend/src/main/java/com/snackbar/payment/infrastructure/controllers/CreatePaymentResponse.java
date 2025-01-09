package com.snackbar.payment.infrastructure.controllers;

import java.math.BigDecimal;

public record CreatePaymentResponse (String orderId, BigDecimal totalDue, String paymentStatus, String paymentMethod, String externalPaymentId){
    
}
