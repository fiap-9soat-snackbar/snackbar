package com.snackbar.payment.application.gateways;

import com.snackbar.payment.domain.entities.Payment;

public interface PaymentGateway {
    Payment createPayment(Payment payment);
}
    