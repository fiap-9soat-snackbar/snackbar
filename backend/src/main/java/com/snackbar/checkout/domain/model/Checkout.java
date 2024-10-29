package com.snackbar.checkout.domain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "checkouts")
public class Checkout {

    @Id
    private String id;
    private String orderId;
    private boolean paid;

    // Getters e Setters
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

    public boolean ispaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
}
