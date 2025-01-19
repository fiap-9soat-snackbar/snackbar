package com.snackbar.payment.application.usecases;

import com.snackbar.payment.application.gateways.PaymentGateway;
import com.snackbar.payment.domain.entity.Payment;

public class GetPaymentByIdUseCase {
    
    private final PaymentGateway paymentGateway;

    public GetPaymentByIdUseCase(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    public Payment getPaymentById(String id) {
        Payment retrievedPayment = paymentGateway.getPaymentById(id);
        return retrievedPayment;
    }

}
