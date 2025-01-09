package com.snackbar.payment.infrastructure.controllers;

import com.snackbar.payment.application.usecases.*;
import com.snackbar.payment.domain.entities.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/* Logging imports
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;*/

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    // Logging definition
    //private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);

    private final CreatePaymentUseCase createPaymentUseCase;
    private final PaymentDTOMapper paymentDTOMapper;

    @Autowired
    public PaymentController(
            CreatePaymentUseCase createPaymentUseCase,
            PaymentDTOMapper paymentDTOMapper) {
        this.createPaymentUseCase = createPaymentUseCase;
        this.paymentDTOMapper = paymentDTOMapper;
    }

    @PostMapping
    public ResponseEntity<CreatePaymentResponse> createPayment(@RequestBody CreatePaymentRequest request) {
        //logger.info("Received request to create payment: {}", request);
        Payment payment = paymentDTOMapper.toDomain(request);
        //logger.info("Payment converted to domain object: {}", payment);
        Payment createdPayment = createPaymentUseCase.createPayment(payment);
        //Payment createdPayment = createPaymentUseCase.createPayment(payment.orderId(), payment.paymentMethod());
        CreatePaymentResponse response = paymentDTOMapper.toResponse(createdPayment);
        //logger.info("Payment created successfully: {}", response);
        return ResponseEntity.ok(response);
    }

}