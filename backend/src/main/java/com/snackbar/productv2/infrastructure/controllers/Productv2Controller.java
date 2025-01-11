package com.snackbar.productv2.infrastructure.controllers;

import com.snackbar.productv2.application.usecases.*;
import com.snackbar.productv2.domain.entity.Productv2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*import org.slf4j.Logger;
import org.slf4j.LoggerFactory;*/

@RestController
@RequestMapping("/api/productsv2")
public class Productv2Controller {

    //private static final Logger logger = LoggerFactory.getLogger(Productv2Controller.class);
    
    private final CreateProductv2UseCase createProductv2UseCase;
    private final GetProductv2ByIdUseCase getProductv2UseCase;
    private final ListProductsv2UseCase listProductsv2UseCase;
    private final GetProductsv2ByCategoryUseCase getProductsv2ByCategoryUseCase;
    private final Productv2DTOMapper productv2DTOMapper;

    @Autowired
    public Productv2Controller(
            CreateProductv2UseCase createProductv2UseCase,
            GetProductv2ByIdUseCase getProductv2UseCase,
            ListProductsv2UseCase listProductsv2UseCase,
            GetProductsv2ByCategoryUseCase getProductsv2ByCategoryUseCase,
            Productv2DTOMapper productv2DTOMapper) {
        this.createProductv2UseCase = createProductv2UseCase;
        this.getProductv2UseCase = getProductv2UseCase;
        this.listProductsv2UseCase = listProductsv2UseCase;
        this.getProductsv2ByCategoryUseCase = getProductsv2ByCategoryUseCase;
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

    @GetMapping
    public ResponseEntity<List<GetProductv2Response>> listProductsv2() {
        List<Productv2> retrievedProductsv2List = listProductsv2UseCase.listProductsv2();
        List<GetProductv2Response> response = productv2DTOMapper.listToResponse(retrievedProductsv2List);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<GetProductv2Response>> getProductsv2ByCategory(@PathVariable("category") String category) {
        List<Productv2> retrievedProductsv2List = getProductsv2ByCategoryUseCase.getProductsv2ByCategory(category);
        List<GetProductv2Response> response = productv2DTOMapper.listToResponse(retrievedProductsv2List);
        return ResponseEntity.ok(response);
    }

}
