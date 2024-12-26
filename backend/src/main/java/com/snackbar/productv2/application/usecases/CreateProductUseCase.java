package com.snackbar.productv2.application.usecases;

import com.snackbar.productv2.application.gateways.ProductGateway;
import com.snackbar.productv2.domain.entity.Product;

// This should be equivalent to a Spring Service definition), 
// but without any framework dependencies. It's also called an Interactor.

public class CreateProductUseCase {
    
    private ProductGateway productGateway;

    public CreateProductUseCase(ProductGateway productGateway) {
        this.productGateway = productGateway;
    }

    public Product createProduct(Product product) {
        return productGateway.createProduct(product);
    }

}
