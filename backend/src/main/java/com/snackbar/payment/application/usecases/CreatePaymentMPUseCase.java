package com.snackbar.payment.application.usecases;

import com.snackbar.payment.application.gateways.PaymentGateway;
import com.snackbar.payment.domain.entity.PaymentMP;
import com.snackbar.payment.domain.entity.PaymentStatus;

public class CreatePaymentMPUseCase {
    
    private final PaymentGateway paymentGateway;
    private final WebHookExecution webHookExecution;

    public CreatePaymentMPUseCase(
            PaymentGateway paymentGateway,
            WebHookExecution webHookExecution
    ) {
        this.paymentGateway = paymentGateway;
        this.webHookExecution = webHookExecution;
    }

    public PaymentMP createPaymentMP(PaymentMP paymentMP){
        PaymentMP createdPaymentMP = paymentGateway.createPaymentMP(paymentMP);
        PaymentStatus paymentStatus = new PaymentStatus(createdPaymentMP.id(), "PAGO", paymentMP.callbackURL());
        this.webHookExecution.webHookExecution(paymentStatus);
        return createdPaymentMP;
    }

}
