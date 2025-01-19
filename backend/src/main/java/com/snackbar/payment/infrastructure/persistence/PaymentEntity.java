package com.snackbar.payment.infrastructure.persistence;

// This should be the Payment entity from the database perspective, not from the domain perspective.

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "payments")
public class PaymentEntity {
    
    @Id
    private String id;
    private String orderId;
    private BigDecimal totalDue;
    private String paymentStatus;
    private String paymentMethod;
    private String externalPaymentId;

    public PaymentEntity(String id, String orderId, BigDecimal totalDue, String paymentStatus, String paymentMethod, String externalPaymentId) {
        this.id = id;
        this.orderId = orderId;
        this.totalDue = totalDue;
        this.paymentStatus = paymentStatus;
        this.paymentMethod = paymentMethod;
        this.externalPaymentId = externalPaymentId;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getTotalDue() {
        return totalDue;
    }
    public void setTotalDue(BigDecimal totalDue) {
        this.totalDue = totalDue;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public void getPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    
    public String getExternalPaymentId() {
        return externalPaymentId;
    }

    public void setExternalPaymentId(String externalPaymentId) {
        this.externalPaymentId = externalPaymentId;
    }
}