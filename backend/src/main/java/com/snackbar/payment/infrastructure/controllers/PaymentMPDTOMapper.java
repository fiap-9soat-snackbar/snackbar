package com.snackbar.payment.infrastructure.controllers;

import com.snackbar.payment.domain.entity.PaymentMP;

public class PaymentMPDTOMapper {
    
    CreatePaymentMPResponse createToResponse(PaymentMP paymentMP) {
        return new CreatePaymentMPResponse(paymentMP.id(), paymentMP.paymentId(), paymentMP.totalDue(), paymentMP.cpf(), paymentMP.callbackURL());
    }

    public PaymentMP createRequestToDomain(CreatePaymentMPRequest request) {
        return new PaymentMP(null, request.paymentId(), request.totalDue(), request.cpf(), request.callbackURL());
    }
}