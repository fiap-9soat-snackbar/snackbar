package com.snackbar.payment.application.usecases;

// This should be equivalent to a Spring Service definition), 
// but without any framework dependencies. It's also called an Interactor.

import com.snackbar.payment.domain.entities.Payment;

public interface CreatePaymentUseCase {
    Payment createPayment(Payment payment);
    //Payment createPayment(String orderId, String paymentMethod);    
}