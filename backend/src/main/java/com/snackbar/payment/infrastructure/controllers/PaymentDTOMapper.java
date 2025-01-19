package com.snackbar.payment.infrastructure.controllers;

import java.math.BigDecimal;
import java.util.List;

import com.snackbar.payment.domain.entity.Payment;
import com.snackbar.payment.domain.entity.PaymentMP;

public class PaymentDTOMapper {

    CreatePaymentResponse createToResponse(Payment payment) {
        return new CreatePaymentResponse(payment.id(), payment.orderId(), payment.totalDue(), payment.paymentStatus(), payment.paymentMethod(), payment.externalPaymentId());
    }

    public Payment createRequestToDomain(CreatePaymentRequest request) {
        
        String myOrderId = request.orderId();
        BigDecimal totalDue = new BigDecimal(0.0);
        String id = null;
        String paymentStatus = null;
        String externalPaymentId = null;
        String myPaymentMethod = request.paymentMethod();
        Payment localPayment = new Payment(id, myOrderId, totalDue, paymentStatus, myPaymentMethod, externalPaymentId);
        return localPayment;
    }    

    GetPaymentResponse getToResponse(Payment payment) {
        return new GetPaymentResponse(payment.id(), payment.orderId(), payment.totalDue(), payment.paymentStatus(), payment.paymentMethod(), payment.externalPaymentId());
    }

    List<GetPaymentResponse> listToResponse(List<Payment> listPayments) {
        return listPayments.stream()
            .map(this::getToResponse)
            .toList();
    }

    public PaymentMP toPaymentMP(Payment payment) {
        if (payment == null) {
            return null;
        }

        String id = null;
        String cpf = "UM_CPF";
        String callbackURL = "http://localhost:8080/api/payment/webhook";

        return new PaymentMP(
                id,
                payment.id(),
                payment.totalDue(),
                cpf,
                callbackURL
        );
    }

    public Payment updateRequestToDomain(UpdatePaymentStatusByExternalIdRequest request) {
        String id = null;
        String orderId = null;
        BigDecimal totalDue = new BigDecimal(0.0);
        String paymentStatus = request.paymentStatus();
        String paymentMethod = null;
        String externalPaymentId = request.externalId();
        return new Payment(id, orderId, totalDue, paymentStatus, paymentMethod, externalPaymentId);
    }

}
