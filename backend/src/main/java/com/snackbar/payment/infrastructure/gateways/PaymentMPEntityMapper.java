package com.snackbar.payment.infrastructure.gateways;

import com.snackbar.payment.domain.entity.PaymentMP;
import com.snackbar.payment.infrastructure.persistence.PaymentMPEntity;

public class PaymentMPEntityMapper {

    PaymentMPEntity toEntity(PaymentMP paymentMPDomainObj) {
        return new PaymentMPEntity(paymentMPDomainObj.id (), paymentMPDomainObj.paymentId (), paymentMPDomainObj.totalDue(), paymentMPDomainObj.cpf(), paymentMPDomainObj.callbackURL());

    }
    
    PaymentMP toDomainObj(PaymentMPEntity paymentMPEntity) {
        return new PaymentMP(paymentMPEntity.getId(), paymentMPEntity.getPaymentId(), paymentMPEntity.getTotalDue(), paymentMPEntity.getCpf(), paymentMPEntity.getCallbackURL());
    }

}