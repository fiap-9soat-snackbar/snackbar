package com.snackbar.payment.application.usecases;

import com.snackbar.payment.application.gateways.PaymentGateway;
import com.snackbar.payment.domain.entity.Payment;
import java.util.List;


public class ListPaymentsUseCase {
    
    private final PaymentGateway payment2Gateway;
    
    public ListPaymentsUseCase(PaymentGateway payment2Gateway) {
            this.payment2Gateway = payment2Gateway;
    }

    public List<Payment> listPayments() {
        return payment2Gateway.listPayments();
    }
}
