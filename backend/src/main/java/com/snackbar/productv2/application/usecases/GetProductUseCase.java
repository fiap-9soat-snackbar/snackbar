package com.snackbar.productv2.application.usecases;

import com.snackbar.productv2.application.gateways.ProductGateway;
import com.snackbar.productv2.domain.entity.Product;

/*import org.slf4j.Logger;
import org.slf4j.LoggerFactory;*/

// This should be equivalent to a Spring Service definition, 
// but without any framework dependencies. It's also called an Interactor.

public class GetProductUseCase {
    
    //private static final Logger logger = LoggerFactory.getLogger(GetProductUseCase.class);

    private final ProductGateway productGateway;

    public GetProductUseCase(ProductGateway productGateway) {
        this.productGateway = productGateway;
    }

    public Product getProduct(Product product) {
        //if (logger != null) logger.info("Starting product retrieval process");
        Product retrievedProduct = productGateway.getProduct(product);
        //if (logger != null) logger.info("Product retrieval completed with id:");
        return retrievedProduct;
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
