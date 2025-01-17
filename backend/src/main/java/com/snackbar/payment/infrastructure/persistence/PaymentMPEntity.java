package com.snackbar.payment.infrastructure.persistence;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "paymentsMP")
public class PaymentMPEntity {
        
    @Id
    private String id;
    private String paymentId;
    private BigDecimal totalDue;
    private String cpf;
    private String callbackURL;

    public PaymentMPEntity(String id, String paymentId, BigDecimal totalDue, String cpf, String callbackURL) {
        this.id = id;
        this.paymentId = paymentId;
        this.totalDue = totalDue;
        this.cpf = cpf;
        this.callbackURL = callbackURL;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public BigDecimal getTotalDue() {
        return totalDue;
    }
    public void setTotalDue(BigDecimal totalDue) {
        this.totalDue = totalDue;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    public String getCallbackURL() {
        return callbackURL;
    }

    public void setCallbackURL(String callbackURL) {
        this.callbackURL = callbackURL;
    }
}