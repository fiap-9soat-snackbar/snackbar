package com.snackbar.payment.application.gateways;

// This should be an abstraction to allow a payment to be created, no matter if in
// memory, in a database, or in a file.

import com.snackbar.payment.domain.entities.Payment;

public interface PaymentGateway {
    Payment createPayment(Payment payment);
}
    