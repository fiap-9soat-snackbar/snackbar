package com.snackbar.productv2.infrastructure.controllers;

import com.snackbar.productv2.application.usecases.*;
import com.snackbar.productv2.domain.entity.Productv2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/*import org.slf4j.Logger;
import org.slf4j.LoggerFactory;*/

@RestController
@RequestMapping("/api/productsv2")
public class Productv2Controller {

    //private static final Logger logger = LoggerFactory.getLogger(Productv2Controller.class);
    
    private final CreateProductv2UseCase createProductv2UseCase;
    private final GetProductv2ByIdUseCase getProductv2UseCase;
    private final Productv2DTOMapper productv2DTOMapper;

    @Autowired
    public Productv2Controller(
            CreateProductv2UseCase createProductv2UseCase,
            GetProductv2ByIdUseCase getProductv2UseCase,
            Productv2DTOMapper productv2DTOMapper) {
        this.createProductv2UseCase = createProductv2UseCase;
        this.getProductv2UseCase = getProductv2UseCase;
        this.productv2DTOMapper = productv2DTOMapper;
    }

    @PostMapping
    public ResponseEntity<CreateProductv2Response> createProductv2(@RequestBody CreateProductv2Request request) {
        //logger.info("Received request to create product: {}", request);
        Productv2 productv2 = productv2DTOMapper.createRequestToDomain(request);
        Productv2 createdProductv2 = createProductv2UseCase.createProductv2(productv2);
        CreateProductv2Response response = productv2DTOMapper.createToResponse(createdProductv2);
        //logger.info("Product created successfully: {}", response);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetProductv2Response> getProductv2(@PathVariable("id") String id) {
        //logger.info("Received request to get product: {}", request);
        Productv2 retrievedProductv2 = getProductv2UseCase.getProductv2ById(id);
        GetProductv2Response response = productv2DTOMapper.getToResponse(retrievedProductv2);
        //logger.info("Product retrieved successfully: {}", response);
        return ResponseEntity.ok(response);
    }
}
