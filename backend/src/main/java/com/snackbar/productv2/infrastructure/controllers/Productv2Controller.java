package com.snackbar.productv2.infrastructure.controllers;

import com.snackbar.productv2.application.usecases.*;
import com.snackbar.productv2.domain.entity.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/*import org.slf4j.Logger;
import org.slf4j.LoggerFactory;*/

@RestController
@RequestMapping("/newapi/products")
public class Productv2Controller {

    //private static final Logger logger = LoggerFactory.getLogger(Productv2Controller.class);
    
    private final CreateProductUseCase createProductUseCase;
    private final ProductDTOMapper productDTOMapper;

    @Autowired
    public Productv2Controller(
            CreateProductUseCase createProductUseCase,
            ProductDTOMapper productDTOMapper) {
        this.createProductUseCase = createProductUseCase;
        this.productDTOMapper = productDTOMapper;
    }

    @PostMapping
    public ResponseEntity<CreateProductResponse> createProduct(@RequestBody CreateProductRequest request) {
        //logger.info("Received request to create product: {}", request);
        Product product = productDTOMapper.toDomain(request);
        Product createdProduct = createProductUseCase.createProduct(product);
        CreateProductResponse response = productDTOMapper.toResponse(createdProduct);
        //logger.info("Product created successfully: {}", response);
        return ResponseEntity.ok(response);
    }
}
