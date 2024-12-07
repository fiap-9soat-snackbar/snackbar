package com.snackbar.pickup.domain.model;

public class Pickup {

    private String id;
    private String orderId;
    private StatusPickup statusPickup;
    private boolean notifyCustomer;

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

    public StatusPickup getStatusPickup() {
        return statusPickup;
    }

    public void setStatusPickup(StatusPickup statusPickup) {
        this.statusPickup = statusPickup;
    }

    public boolean isNotifyCustomer() {
        return notifyCustomer;
    }

    public void setNotifyCustomer(boolean notifyCustomer) {
        this.notifyCustomer = notifyCustomer;
    }
}
