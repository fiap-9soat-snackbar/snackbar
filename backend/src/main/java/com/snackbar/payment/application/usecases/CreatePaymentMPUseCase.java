package com.snackbar.payment.application.usecases;

import com.snackbar.payment.application.gateways.PaymentGateway;
import com.snackbar.payment.domain.entity.PaymentMP;

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
        this.webHookExecution.webHookExecution(paymentMP);
        return createdPaymentMP;
    }

}
