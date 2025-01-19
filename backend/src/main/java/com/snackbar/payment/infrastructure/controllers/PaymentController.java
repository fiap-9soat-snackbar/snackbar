package com.snackbar.payment.infrastructure.controllers;

import com.snackbar.payment.application.usecases.*;
import com.snackbar.payment.domain.entity.Payment;
import com.snackbar.payment.domain.entity.PaymentMP;
import com.snackbar.payment.infrastructure.MpService;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final CreatePaymentUseCase createPaymentUseCase;
    private final ListPaymentsUseCase listPaymentsUseCase;
    private final CreatePaymentMPUseCase createPaymentMPUseCase;
    private final PaymentDTOMapper paymentDTOMapper;
    private final PaymentMPDTOMapper paymentMPDTOMapper;
    private final MpService mpService;
    private final GetPaymentByIdUseCase getPaymentByIdUseCase;
    private final UpdatePaymentExternalIdByIdUseCase updatePaymentExternalIdByIdUseCase;

    @Autowired
    public PaymentController(
            CreatePaymentUseCase createPaymentUseCase,
            ListPaymentsUseCase listPaymentsUseCase,
            CreatePaymentMPUseCase createPaymentMPUseCase,
            PaymentDTOMapper paymentDTOMapper,
            PaymentMPDTOMapper paymentMPDTOMapper,
            GetPaymentByIdUseCase getPaymentByIdUseCase,
            UpdatePaymentExternalIdByIdUseCase updatePaymentExternalIdByIdUseCase,
            MpService mpService) {
        this.createPaymentUseCase = createPaymentUseCase;
        this.listPaymentsUseCase = listPaymentsUseCase;
        this.createPaymentMPUseCase = createPaymentMPUseCase;
        this.paymentDTOMapper = paymentDTOMapper;
        this.paymentMPDTOMapper = paymentMPDTOMapper;
        this.mpService = mpService;
        this.getPaymentByIdUseCase = getPaymentByIdUseCase;
        this.updatePaymentExternalIdByIdUseCase = updatePaymentExternalIdByIdUseCase;
    }

    @PostMapping
    public ResponseEntity<CreatePaymentResponse> createPayment(@RequestBody CreatePaymentRequest request) {

        Payment payment = paymentDTOMapper.createRequestToDomain(request);
        Payment createdPayment = createPaymentUseCase.createPayment(payment);
        var res = mpService.postMercadoPago(paymentDTOMapper.toPaymentMP(createdPayment));
        
        // Parse the JSON response using Gson
        JsonObject jsonObject = JsonParser.parseString(res).getAsJsonObject();
        String externalPaymentId = jsonObject.get("id").getAsString();
        String paymentId = jsonObject.get("paymentId").getAsString();

        Payment updatedPayment = updatePaymentExternalIdByIdUseCase.updatePaymentExternalIdById(paymentId,externalPaymentId);
        // lookup com cpf
        // gerar url
        CreatePaymentResponse response = paymentDTOMapper.createToResponse(updatedPayment);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<GetPaymentResponse>> listPayments() {
        List<Payment> retrievedPaymentsList = listPaymentsUseCase.listPayments();
        List<GetPaymentResponse> response = paymentDTOMapper.listToResponse(retrievedPaymentsList);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<GetPaymentResponse> getPaymentById(@PathVariable("id") String id) {
        Payment retrievedPayment = getPaymentByIdUseCase.getPaymentById(id);
        GetPaymentResponse response = paymentDTOMapper.getToResponse(retrievedPayment);
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
