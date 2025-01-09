package com.snackbar.payment.infrastructure.config;

import com.snackbar.payment.application.gateways.PaymentGateway;
import com.snackbar.payment.application.usecases.CreatePaymentUseCase;
import com.snackbar.payment.infrastructure.controllers.PaymentDTOMapper;
import com.snackbar.payment.infrastructure.gateways.PaymentEntityMapper;
import com.snackbar.payment.infrastructure.gateways.PaymentRepositoryGateway;
import com.snackbar.payment.infrastructure.persistence.PaymentRepository;

import com.snackbar.order.service.OrderService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/* Logging imports
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.annotation.PostConstruct;*/

@Configuration
public class PaymentConfig {
    
    // Logging definition
    //private static final Logger logger = LoggerFactory.getLogger(PaymentConfig.class);
    
    /*
    @PostConstruct
    public void init() {
        logger.info("PaymentConfig initialized");
    }*/

    @Bean
    public CreatePaymentUseCase createPaymentUseCase(PaymentGateway paymentGateway, OrderService orderService) {
        // Logging
        //logger.info("Creating CreatePaymentUseCase bean");
        return new CreatePaymentUseCase(paymentGateway, orderService);
    }
    
    @Bean
    public PaymentGateway paymentGateway(PaymentRepository paymentRepository, PaymentEntityMapper paymentEntityMapper) {
        // Logging
        //logger.info("Creating PaymenttGateway bean");
        return new PaymentRepositoryGateway(paymentRepository, paymentEntityMapper);
    }

    @Bean
    public PaymentEntityMapper paymentEntityMapper() {
        // Logging
        //logger.info("Creating PaymentEntityMapper bean");
        return new PaymentEntityMapper();
    }

    @Bean
    public PaymentDTOMapper paymentDTOMapper() {
        // Logging
        //logger.info("Creating PaymentDTOMapper bean");
        return new PaymentDTOMapper();
    }
}
