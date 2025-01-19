package com.snackbar.payment.application.usecases;

import com.snackbar.payment.application.gateways.PaymentGateway;
import com.snackbar.payment.domain.entity.PaymentMP;


public class WebHookExecution {

    private final PaymentGateway paymentGateway;

    public WebHookExecution(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    public void webHookExecution(PaymentMP paymentMP){
        paymentGateway.webHookExecution(paymentMP);
    }

}
