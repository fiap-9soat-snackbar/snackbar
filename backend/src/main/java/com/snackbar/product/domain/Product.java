package com.snackbar.product.domain;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
public class Product {
    @Id
    private String id;
    private String category;
    
    public static final List<String> VALID_CATEGORIES = Arrays.asList("Lanche", "Acompanhamento", "Bebida", "Sobremesa");

    public void validateProduct() {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name is required");
        }
        if (category == null || !VALID_CATEGORIES.contains(category)) {
            throw new IllegalArgumentException("Invalid product category. Valid categories are: " + String.join(", ", VALID_CATEGORIES));
        }
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Product description is required");
        }
        if (price == null) {
            throw new IllegalArgumentException("Product price is required");
        }
        if (price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Product price must be zero or greater");
        }
        if (cookingTime == null || cookingTime <= 0) {
            throw new IllegalArgumentException("Product cooking time must be a positive integer");
        }
    }
    private String description;
    private String name;
    private BigDecimal price;
    private Integer cookingTime;

    public Product() {}

    public Product(String name, String category, String description, BigDecimal price, Integer cookingTime) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.price = price;
        this.cookingTime = cookingTime;
    }

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