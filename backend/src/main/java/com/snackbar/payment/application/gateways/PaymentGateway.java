package com.snackbar.payment.application.gateways;

import com.snackbar.payment.domain.entity.Payment;
import com.snackbar.payment.domain.entity.PaymentMP;

import java.util.List;

public interface PaymentGateway {
    Payment createPayment(Payment payment);
    List<Payment> listPayments();
    PaymentMP createPaymentMP(PaymentMP paymentMP);
    Payment getPaymentById(String id);
    Payment updatePaymentExternalIdById(String id, String externalPaymentId);
    Payment getPaymentByExternalId(String externalId);
    Payment updatePaymentStatusByExternalId(String externalId, String paymentStatus);
}
    