package com.snackbar.payment.infrastructure.controllers;

public record UpdatePaymentStatusByExternalIdRequest (
    String externalId,
    String paymentStatus
) {}
