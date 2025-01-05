package com.snackbar.product.entity;

import java.math.BigDecimal;

public abstract class BaseProduct {
    protected String id;
    protected String name;
    protected String category;
    protected String description;
    protected BigDecimal price;
    protected Integer cookingTime;

    public BaseProduct() {}

    public BaseProduct(String name, String category, String description, BigDecimal price, Integer cookingTime) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.price = price;
        this.cookingTime = cookingTime;
    }

    // Common getters and setters
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(Integer cookingTime) {
        this.cookingTime = cookingTime;
    }
}