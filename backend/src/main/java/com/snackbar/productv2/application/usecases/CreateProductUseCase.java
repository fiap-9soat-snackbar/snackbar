package com.snackbar.productv2.application.usecases;

import com.snackbar.productv2.application.gateways.ProductGateway;
import com.snackbar.productv2.domain.entity.Product;

/*import org.slf4j.Logger;
import org.slf4j.LoggerFactory;*/

// This should be equivalent to a Spring Service definition), 
// but without any framework dependencies. It's also called an Interactor.

public class CreateProductUseCase {
    
    //private static final Logger logger = LoggerFactory.getLogger(CreateProductUseCase.class);

    private final ProductGateway productGateway;

    public CreateProductUseCase(ProductGateway productGateway) {
        this.productGateway = productGateway;
    }

    public Product createProduct(Product product) {
        //if (logger != null) logger.info("Starting product creation process");
        Product createdProduct = productGateway.createProduct(product);
        //if (logger != null) logger.info("Product creation completed with id:");
        return createdProduct;
    }

}
