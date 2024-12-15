package com.snackbar.product.entity;

import com.snackbar.product.common.exception.BusinessException;
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
            throw new BusinessException("Product name is required");
        }
        if (getCategory() == null || getCategory().trim().isEmpty()) {
            throw new BusinessException("Product category is required");
        }
        if (getPrice() == null || getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BusinessException("Product price must be greater than zero");
        }
    }

    public Product() {
        super();
    }

    public Product(String name, String category, String description, BigDecimal price, Integer cookingTime) {
        super(name, category, description, price, cookingTime);
    }
}