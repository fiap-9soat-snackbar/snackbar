package com.snackbar.productv2.application.usecases;

import java.util.List;

import com.snackbar.productv2.application.gateways.Productv2Gateway;
import com.snackbar.productv2.domain.entity.Productv2;

/*import org.slf4j.Logger;
import org.slf4j.LoggerFactory;*/

// This should be equivalent to a Spring Service definition, 
// but without any framework dependencies. It's also called an Interactor.

public class ListProductsv2UseCase {

    private final Productv2Gateway productv2Gateway;
    
    public ListProductsv2UseCase(Productv2Gateway productv2Gateway) {
            this.productv2Gateway = productv2Gateway;
    }

    public List<Productv2> listProductsv2() {
        return productv2Gateway.listProductsv2();
    }
}
