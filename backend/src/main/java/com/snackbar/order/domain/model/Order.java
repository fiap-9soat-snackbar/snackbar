package com.snackbar.order.domain.model;

import java.time.Instant;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;


@Document(collection = "orders")

public class Order {

    @Id

    private String id;
    private int orderNumber;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Instant orderDateTime;
    private Double customerId;
    private String clientName;
    private List<Item> items; 
    private StatusOrder statusOrder;
    private int waitingTime;
    private double totalPrice;    
   

    // Getters e Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
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
        return waitingTime;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public StatusOrder getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(StatusOrder statusOrder) {
        this.statusOrder = statusOrder;
    }
}

class Item {
    private String name;
    private BigDecimal price;
    private Integer quantity;
    private Integer cookingTime;
    private String customization;

    // Getters e Setters para os campos da classe Item
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(Integer cookingTime) {
        this.cookingTime = cookingTime;
    }

    public String getCustomization() {
        return customization;
    }

    public void setCustomization(String customization) {
        this.customization = customization;
    }
}