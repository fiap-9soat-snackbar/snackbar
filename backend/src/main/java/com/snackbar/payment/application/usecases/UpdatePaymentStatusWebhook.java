package com.snackbar.payment.application.usecases;

import com.snackbar.payment.application.gateways.PaymentGateway;
import com.snackbar.payment.domain.entity.Payment;

public class UpdatePaymentStatusWebhook {

    private final PaymentGateway paymentGateway;
    private final GetPaymentByExternalIdUseCase getPaymentByExternalIdUseCase;

    public UpdatePaymentStatusWebhook(PaymentGateway paymentGateway,
    GetPaymentByExternalIdUseCase getPaymentByExternalIdUseCase) {
        this.paymentGateway = paymentGateway;
        this.getPaymentByExternalIdUseCase = getPaymentByExternalIdUseCase;
    }

    public Payment updatePaymentStatus(String externalId, String paymentStatus) {
        Payment locatedPayment = getPaymentByExternalIdUseCase.getPaymentByExternalId(externalId);
        Payment updatedPayment = paymentGateway.updatePaymentStatusByExternalId(locatedPayment.externalPaymentId(), paymentStatus);
        return updatedPayment;
    }

}