package com.snackbar.payment.application.usecases;

import com.snackbar.payment.application.gateways.PaymentGateway;
import com.snackbar.payment.domain.entity.PaymentMP;

public class CreatePaymentMPUseCase {
    
    private final PaymentGateway paymentGateway;

    public CreatePaymentMPUseCase(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    public PaymentMP createPaymentMP(PaymentMP paymentMP){
        PaymentMP createdPaymentMP = paymentGateway.createPaymentMP(paymentMP);
        return createdPaymentMP;
    }

}