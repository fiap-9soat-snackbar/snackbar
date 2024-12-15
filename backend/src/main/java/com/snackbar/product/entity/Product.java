package com.snackbar.product.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.math.BigDecimal;

@Document(collection = "products")
public class Product extends BaseProduct {
    @Id
    @Override
    public String getId() {
        return super.getId();
    }

    public void validateProduct() {
        if (getName() == null || getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be empty");
        }
        if (getCategory() == null || getCategory().trim().isEmpty()) {
            throw new IllegalArgumentException("Product category cannot be empty");
        }
        if (getPrice() == null || getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Product price must be greater than zero");
        }
        if (getCookingTime() == null || getCookingTime() <= 0) {
            throw new IllegalArgumentException("Cooking time must be greater than zero");
        }
    }

    public Product() {
        super();
    }

    public Product(String name, String category, String description, BigDecimal price, Integer cookingTime) {
        super(name, category, description, price, cookingTime);
    }
}