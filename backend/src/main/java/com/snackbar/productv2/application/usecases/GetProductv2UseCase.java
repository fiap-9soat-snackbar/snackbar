package com.snackbar.productv2.application.usecases;

import com.snackbar.productv2.application.gateways.Productv2Gateway;
import com.snackbar.productv2.domain.entity.Productv2;

/*import org.slf4j.Logger;
import org.slf4j.LoggerFactory;*/

// This should be equivalent to a Spring Service definition, 
// but without any framework dependencies. It's also called an Interactor.

public class GetProductv2UseCase {
    
    //private static final Logger logger = LoggerFactory.getLogger(GetProductUseCase.class);

    private final Productv2Gateway productv2Gateway;

    public GetProductv2UseCase(Productv2Gateway productv2Gateway) {
        this.productv2Gateway = productv2Gateway;
    }

    public Productv2 getProductv2(Productv2 productv2) {
        //if (logger != null) logger.info("Starting product retrieval process");
        Productv2 retrievedProductv2 = productv2Gateway.getProductv2(productv2);
        //if (logger != null) logger.info("Product retrieval completed with id:");
        return retrievedProductv2;
    }

    /*
    public Payment createPayment(Payment payment){
        //logger.info("Finding order: {}", orderId);
        Order order = orderService.searchOrderId(payment.orderId());
        //Order order = orderService.searchOrderId(orderId);
        //logger.info("Obtaining total price for order: {}", order.getTotalPrice);
        BigDecimal totalDue = order.getTotalPrice();
        //logger.info("Setting payment status and temporary external payment ID:");
        String paymentStatus = "PENDENTE";
        String externalPaymentId = "PENDENTE";
        Payment localPayment = new Payment(payment.orderId(), totalDue, paymentStatus, payment.paymentMethod(), externalPaymentId);
        Payment createdPayment = paymentGateway.createPayment(localPayment);
        return createdPayment;
    }*/

}
