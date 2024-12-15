package com.snackbar.product.dto;

import com.snackbar.product.entity.BaseProduct;
import java.math.BigDecimal;

public class ProductDTO extends BaseProduct {
    public ProductDTO() {
        super();
    }

    public ProductDTO(String id, String name, String category, String description, BigDecimal price, Integer cookingTime) {
        super(name, category, description, price, cookingTime);
        this.setId(id);
    }
}