package com.snackbar.payment.infrastructure.controllers;

import com.snackbar.payment.application.usecases.*;
import com.snackbar.payment.domain.entity.Payment;
import com.snackbar.payment.domain.entity.PaymentMP;

import java.util.List;

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
    private final ListPaymentsUseCase listPaymentsUseCase;
    private final CreatePaymentMPUseCase createPaymentMPUseCase;
    private final PaymentDTOMapper paymentDTOMapper;
    private final PaymentMPDTOMapper paymentMPDTOMapper;

    @Autowired
    public PaymentController(
            CreatePaymentUseCase createPaymentUseCase,
            ListPaymentsUseCase listPaymentsUseCase,
            CreatePaymentMPUseCase createPaymentMPUseCase,
            PaymentDTOMapper paymentDTOMapper,
            PaymentMPDTOMapper paymentMPDTOMapper) {
        this.createPaymentUseCase = createPaymentUseCase;
        this.listPaymentsUseCase = listPaymentsUseCase;
        this.createPaymentMPUseCase = createPaymentMPUseCase;
        this.paymentDTOMapper = paymentDTOMapper;
        this.paymentMPDTOMapper = paymentMPDTOMapper;
    }

    @PostMapping
    public ResponseEntity<CreatePaymentResponse> createPayment(@RequestBody CreatePaymentRequest request) {
        //logger.info("Received request to create payment: {}", request);
        Payment payment = paymentDTOMapper.createRequestToDomain(request);
        //logger.info("Payment converted to domain object: {}", payment);
        Payment createdPayment = createPaymentUseCase.createPayment(payment);
        //Payment createdPayment = createPaymentUseCase.createPayment(payment.orderId(), payment.paymentMethod());
        CreatePaymentResponse response = paymentDTOMapper.createToResponse(createdPayment);
        //logger.info("Payment created successfully: {}", response);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<GetPaymentResponse>> listPayments() {
        List<Payment> retrievedPaymentsList = listPaymentsUseCase.listPayments();
        List<GetPaymentResponse> response = paymentDTOMapper.listToResponse(retrievedPaymentsList);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/mercadopago")
    public ResponseEntity<CreatePaymentMPResponse> createPaymentMP(@RequestBody CreatePaymentMPRequest request) {
        PaymentMP paymentMP = paymentMPDTOMapper.createRequestToDomain(request);
        PaymentMP createdPaymentMP = createPaymentMPUseCase.createPaymentMP(paymentMP);
        CreatePaymentMPResponse response = paymentMPDTOMapper.createToResponse(createdPaymentMP);
        return ResponseEntity.ok(response);
    }

}