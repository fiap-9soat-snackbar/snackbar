package com.snackbar.payment.infrastructure.gateways;

import com.snackbar.payment.domain.entities.Payment;
import com.snackbar.payment.infrastructure.persistence.PaymentEntity;

public class PaymentEntityMapper {

    PaymentEntity toEntity(Payment paymentDomainObj) {
        return new PaymentEntity(paymentDomainObj.id (), paymentDomainObj.orderId (), paymentDomainObj.totalDue(), paymentDomainObj.paymentStatus(), paymentDomainObj.paymentMethod(), paymentDomainObj.externalPaymentId());

    }
    
    Payment toDomainObj(PaymentEntity paymentEntity) {
        return new Payment(paymentEntity.getId(), paymentEntity.getOrderId(), paymentEntity.getTotalDue(), paymentEntity.getPaymentStatus(), paymentEntity.getPaymentMethod(), paymentEntity.getExternalPaymentId());
    }
}