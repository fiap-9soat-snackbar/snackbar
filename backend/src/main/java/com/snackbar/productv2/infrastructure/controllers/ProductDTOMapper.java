package com.snackbar.productv2.infrastructure.controllers;

import com.snackbar.productv2.domain.entity.Product;    

public class ProductDTOMapper {
    CreateProductResponse toResponse(Product product) {
        return new CreateProductResponse(product.name(), product.category(), product.description(), product.price(), product.cookingTime());
    }

    public Product toProduct(CreateProductRequest request) {
        return new Product(request.name(), request.category(), request.description(), request.price(), request.cookingTime());
    }
}
