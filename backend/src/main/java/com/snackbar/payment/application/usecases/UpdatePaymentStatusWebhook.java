package com.snackbar.payment.application.usecases;

import com.snackbar.order.domain.model.Order;
import com.snackbar.orderRefactory.application.usecases.OrderUseCase;
import com.snackbar.payment.application.gateways.PaymentGateway;
import com.snackbar.payment.domain.entity.Payment;

public class UpdatePaymentStatusWebhook {

    private final PaymentGateway paymentGateway;
    private final GetPaymentByExternalIdUseCase getPaymentByExternalIdUseCase;
    private final OrderUseCase orderUseCase;

    public UpdatePaymentStatusWebhook(PaymentGateway paymentGateway,
    GetPaymentByExternalIdUseCase getPaymentByExternalIdUseCase,
    OrderUseCase orderUseCase) {
        this.paymentGateway = paymentGateway;
        this.getPaymentByExternalIdUseCase = getPaymentByExternalIdUseCase;
        this.orderUseCase = orderUseCase;
    }

    public Payment updatePaymentStatus(String externalId, String paymentStatus) {
        Payment locatedPayment = getPaymentByExternalIdUseCase.getPaymentByExternalId(externalId);
        Payment updatedPayment = paymentGateway.updatePaymentStatusByExternalId(locatedPayment.externalPaymentId(), paymentStatus);
        orderUseCase.updateStatusOrder(updatedPayment.orderId(), paymentStatus);
        return updatedPayment;
    }

}