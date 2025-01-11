package com.snackbar.productv2.application.usecases;

import com.snackbar.productv2.application.gateways.Productv2Gateway;
import com.snackbar.productv2.domain.entity.Productv2;

/*import org.slf4j.Logger;
import org.slf4j.LoggerFactory;*/

// This should be equivalent to a Spring Service definition, 
// but without any framework dependencies. It's also called an Interactor.

public class GetProductv2ByIdUseCase {
    
    //private static final Logger logger = LoggerFactory.getLogger(GetProductUseCase.class);

    private final Productv2Gateway productv2Gateway;

    public GetProductv2ByIdUseCase(Productv2Gateway productv2Gateway) {
        this.productv2Gateway = productv2Gateway;
    }

    public Productv2 getProductv2ById(String id) {
        Productv2 retrievedProductv2 = productv2Gateway.getProductv2ById(id);
        return retrievedProductv2;
    }

}
