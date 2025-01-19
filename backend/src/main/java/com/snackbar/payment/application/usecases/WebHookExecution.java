package com.snackbar.payment.application.usecases;

import com.snackbar.payment.application.gateways.PaymentGateway;
import com.snackbar.payment.domain.entity.PaymentMP;
import com.snackbar.payment.domain.entity.PaymentStatus;


public class WebHookExecution {

    private final PaymentGateway paymentGateway;

    public WebHookExecution(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    public void webHookExecution(PaymentStatus paymentStatus){
        paymentGateway.webHookExecution(paymentStatus);
    }

}
