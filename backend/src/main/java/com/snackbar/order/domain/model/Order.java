package com.snackbar.order.domain.model;

import java.time.Instant;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;


@Document(collection = "orders")

public class Order {

    @Id

    private String id;
    private String orderNumber;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Instant orderDateTime;
    private Double customerId;
    private String clientName;
    private List<Item> items = new ArrayList<>(); 
    private StatusOrder statusOrder;
    private BigDecimal totalPrice;    
   

    // Getters e Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Instant getOrderDateTime() {
        return orderDateTime;
    }

    public void setOrderDateTime(Instant orderDateTime) {
        this.orderDateTime = orderDateTime;
    }

    public Double getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Double customerId) {
        this.customerId = customerId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }


    public int getWaitingTime() {
        return items.stream()
                .mapToInt(item -> item.getCookingTime() * item.getQuantity())
                .sum();
    }

    // Removed setWaitingTime as it's now calculated dynamically

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public StatusOrder getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(StatusOrder statusOrder) {
        this.statusOrder = statusOrder;
    }

    public static String generateOrderNumber(String lastOrderNumber) {
        if (lastOrderNumber == null || lastOrderNumber.isEmpty()) {
            return "000001";
        }
        int nextNumber = Integer.parseInt(lastOrderNumber) + 1;
        return String.format("%06d", nextNumber);
    }
}

// Item class has been moved to a separate file