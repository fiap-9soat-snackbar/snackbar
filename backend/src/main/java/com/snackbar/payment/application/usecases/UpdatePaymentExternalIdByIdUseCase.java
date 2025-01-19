package com.snackbar.payment.application.usecases;

import com.snackbar.payment.application.gateways.PaymentGateway;
import com.snackbar.payment.domain.entity.Payment;

public class UpdatePaymentExternalIdByIdUseCase {
    
    private final PaymentGateway paymentGateway;
    private final GetPaymentByIdUseCase getPaymentByIdUseCase;

    public UpdatePaymentExternalIdByIdUseCase(PaymentGateway paymentGateway,
                                              GetPaymentByIdUseCase getPaymentByIdUseCase) {
        this.paymentGateway = paymentGateway;
        this.getPaymentByIdUseCase = getPaymentByIdUseCase;
    }

    public Payment updatePaymentExternalIdById(String id, String externalPaymentId) {
        Payment locatedPayment = getPaymentByIdUseCase.getPaymentById(id);
        Payment updatedPayment = paymentGateway.updatePaymentExternalIdById(locatedPayment.id(), externalPaymentId);
        return updatedPayment;
    }
}
