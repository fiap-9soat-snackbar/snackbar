package com.snackbar.cooking.domain.entity;

import java.time.LocalDateTime;
import java.time.Duration;
import java.util.List;

public class Cooking {

    private String id;
    private String name;
    private String cpf;
    private String orderNumber;
    private LocalDateTime orderDateTime;
    private List<Item> items;
    private String statusOrder;
    private int waitingTime;
    private long remainingWaitingTime;
    private String paymentMethod;
    private int totalPrice;

    public void updateRemainingWaitingTime() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime deliverTime = orderDateTime.plusMinutes(waitingTime);
        remainingWaitingTime = Duration.between(currentDateTime, deliverTime).toMinutes();
        remainingWaitingTime = Math.max(remainingWaitingTime, 0);
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public LocalDateTime getOrderDateTime() {
        return orderDateTime;
    }

    public void setOrderDateTime(LocalDateTime orderDateTime) {
        this.orderDateTime = orderDateTime;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(String statusOrder) {
        this.statusOrder = statusOrder;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    public long getRemainingWaitingTime() {
        return remainingWaitingTime;
    }

    public void setRemainingWaitingTime(long remainingWaitingTime) {
        this.remainingWaitingTime = remainingWaitingTime;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public static class Item {
        private String name;
        private int price;
        private int quantity;
        private int cookingTime;
        private String customization;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public int getCookingTime() {
            return cookingTime;
        }

        public void setCookingTime(int cookingTime) {
            this.cookingTime = cookingTime;
        }

        public String getCustomization() {
            return customization;
        }

        public void setCustomization(String customization) {
            this.customization = customization;
        }
    }
}