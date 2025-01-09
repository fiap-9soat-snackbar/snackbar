package com.snackbar.payment.infrastructure.controllers;

import java.math.BigDecimal;

import com.snackbar.payment.domain.entities.Payment;  

public class PaymentDTOMapper {

    CreatePaymentResponse toResponse(Payment payment) {
        return new CreatePaymentResponse(payment.orderId(), payment.totalDue(), payment.paymentStatus(), payment.paymentMethod(), payment.externalPaymentId());
    }

    public Payment toDomain(CreatePaymentRequest request) {
        
        String myOrderId = request.orderId();
        BigDecimal totalDue = new BigDecimal(0.0);
        String paymentStatus = null;
        String externalPaymentId = null;
        String myPaymentMethod = request.paymentMethod();
        Payment localPayment = new Payment(myOrderId, totalDue, paymentStatus, myPaymentMethod, externalPaymentId);
        return localPayment;
    }    

}