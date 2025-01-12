package com.snackbar.payment.application.gateways;

import java.util.List;

import com.snackbar.payment.domain.entity.Payment;

public interface PaymentGateway {
    Payment createPayment(Payment payment);
        List<Payment> listPayments();
}
    