package com.snackbar.productv2.infrastructure.controllers;

import com.snackbar.productv2.application.usecases.*;
import com.snackbar.productv2.domain.entity.Product;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/newapi/products")
public class Productv2Controller {

    private final CreateProductUseCase createProductUseCase;
    private final ProductDTOMapper productDTOMapper;

    public Productv2Controller(
            CreateProductUseCase createProductUseCase,
            ProductDTOMapper productDTOMapper) {
        this.createProductUseCase = createProductUseCase;
        this.productDTOMapper = productDTOMapper;
    }

    @PostMapping
    CreateProductResponse create(@RequestBody CreateProductRequest request) {
        Product productBusinessObj = productDTOMapper.toProduct(request);
        Product product = createProductUseCase.createProduct(productBusinessObj);
        return productDTOMapper.toResponse(product);
    }
}
