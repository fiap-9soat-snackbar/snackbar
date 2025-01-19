package com.snackbar.payment.infrastructure.gateways;

import java.util.List;

import com.snackbar.payment.domain.entity.Payment;
import com.snackbar.payment.infrastructure.persistence.PaymentEntity;

public class PaymentEntityMapper {

    PaymentEntity toEntity(Payment paymentDomainObj) {
        return new PaymentEntity(paymentDomainObj.id (), paymentDomainObj.orderId (), paymentDomainObj.totalDue(), paymentDomainObj.paymentStatus(), paymentDomainObj.paymentMethod(), paymentDomainObj.externalPaymentId());

    }
    
    Payment toDomainObj(PaymentEntity paymentEntity) {
        return new Payment(paymentEntity.getId(), paymentEntity.getOrderId(), paymentEntity.getTotalDue(), paymentEntity.getPaymentStatus(), paymentEntity.getPaymentMethod(), paymentEntity.getExternalPaymentId());
    }

    List<Payment> toDomainListObj(List<PaymentEntity> paymentEntityList) {
        return paymentEntityList.stream()
            .map(this::toDomainObj)
            .toList();
    }
}