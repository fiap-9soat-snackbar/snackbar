package com.snackbar.payment.infrastructure.controllers;

import java.math.BigDecimal;

import com.snackbar.payment.domain.entities.Payment;  

public class PaymentDTOMapper {

    CreatePaymentResponse createToResponse(Payment payment) {
        return new CreatePaymentResponse(payment.id(), payment.orderId(), payment.totalDue(), payment.paymentStatus(), payment.paymentMethod(), payment.externalPaymentId());
    }

    public Payment createRequestToDomain(CreatePaymentRequest request) {
        
        String myOrderId = request.orderId();
        BigDecimal totalDue = new BigDecimal(0.0);
        String id = null;
        String paymentStatus = null;
        String externalPaymentId = null;
        String myPaymentMethod = request.paymentMethod();
        Payment localPayment = new Payment(id, myOrderId, totalDue, paymentStatus, myPaymentMethod, externalPaymentId);
        return localPayment;
    }    

}