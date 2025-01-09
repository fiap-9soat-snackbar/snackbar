package com.snackbar.productv2.infrastructure.controllers;

import com.snackbar.productv2.domain.entity.Product;    

public class ProductDTOMapper {
    CreateProductResponse createToResponse(Product product) {
        return new CreateProductResponse(product.name(), product.category(), product.description(), product.price(), product.cookingTime());
    }

    public Product createRequestToDomain(CreateProductRequest request) {
        return new Product(request.name(), request.category(), request.description(), request.price(), request.cookingTime());
    }

    GetProductResponse getToResponse(Product product) {
        return new GetProductResponse(product.name(), product.category(), product.description(), product.price(), product.cookingTime());
    }
    
    public Product getRequestToDomain(GetProductRequest request) {
        return new Product(request.id(), null, null, null, null);

    }
}
