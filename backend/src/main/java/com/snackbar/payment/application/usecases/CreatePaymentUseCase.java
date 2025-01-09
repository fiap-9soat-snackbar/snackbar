package com.snackbar.payment.application.usecases;

// This should be equivalent to a Spring Service definition, 
// but without any framework dependencies. It's also called an Interactor.

import com.snackbar.order.domain.model.Order;
import com.snackbar.order.service.OrderService;
import com.snackbar.payment.application.gateways.PaymentGateway;
import com.snackbar.payment.domain.entities.Payment;
import java.math.BigDecimal;

/*import org.slf4j.Logger;
import org.slf4j.LoggerFactory;*/

public class CreatePaymentUseCase {

    // Logging definition
    //private static final Logger logger = LoggerFactory.getLogger(CreatePaymentUseCaseImpl.class);
    
    private final PaymentGateway paymentGateway;
    private final OrderService orderService;

    public CreatePaymentUseCase(PaymentGateway paymentGateway, OrderService orderService) {
        this.paymentGateway = paymentGateway;
        this.orderService = orderService;
    }

    public Payment createPayment(Payment payment){
    //public Payment createPayment(String orderId, String paymentMethod){
        //logger.info("Finding order: {}", orderId);
        Order order = orderService.searchOrderId(payment.orderId());
        //Order order = orderService.searchOrderId(orderId);
        //logger.info("Obtaining total price for order: {}", order.getTotalPrice);
        BigDecimal totalDue = order.getTotalPrice();
        //logger.info("Setting payment status and temporary external payment ID:");
        String paymentStatus = "PENDENTE";
        String externalPaymentId = "PENDENTE";
        Payment localPayment = new Payment(payment.orderId(), totalDue, paymentStatus, payment.paymentMethod(), externalPaymentId);
        //Payment localPayment = new Payment(orderId, totalDue, paymentStatus, paymentMethod, externalPaymentId);
        Payment createdPayment = paymentGateway.createPayment(localPayment);
        return createdPayment;
    }

}