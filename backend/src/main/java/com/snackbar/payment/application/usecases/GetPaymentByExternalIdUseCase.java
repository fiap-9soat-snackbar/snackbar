package com.snackbar.payment.application.usecases;

import com.snackbar.payment.application.gateways.PaymentGateway;
import com.snackbar.payment.domain.entity.Payment;

public class GetPaymentByExternalIdUseCase {
    
    private final PaymentGateway paymentGateway;

    public GetPaymentByExternalIdUseCase(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    public Payment getPaymentByExternalId(String externalId) {
        Payment retrievedPayment = paymentGateway.getPaymentByExternalId(externalId);
        return retrievedPayment;
    }
}
