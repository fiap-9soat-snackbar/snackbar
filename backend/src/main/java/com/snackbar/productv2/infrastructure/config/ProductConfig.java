package com.snackbar.productv2.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.snackbar.productv2.application.gateways.Productv2Gateway;
import com.snackbar.productv2.application.usecases.CreateProductv2UseCase;
import com.snackbar.productv2.application.usecases.GetProductv2UseCase;
import com.snackbar.productv2.infrastructure.controllers.Productv2DTOMapper;
import com.snackbar.productv2.infrastructure.gateways.Productv2EntityMapper;
import com.snackbar.productv2.infrastructure.gateways.Productv2RepositoryGateway;
import com.snackbar.productv2.infrastructure.persistence.Productv2Repository;

@Configuration
public class ProductConfig {
    @Bean
    CreateProductv2UseCase createv2ProductUseCase(Productv2Gateway productv2Gateway) {
        return new CreateProductv2UseCase(productv2Gateway);
    }

    @Bean
    GetProductv2UseCase getProductv2UseCase(Productv2Gateway productv2Gateway) {
        return new GetProductv2UseCase(productv2Gateway);
    }
    
    @Bean
    Productv2Gateway productv2Gateway(Productv2Repository productv2Repository, Productv2EntityMapper productv2EntityMapper) {
        return new Productv2RepositoryGateway(productv2Repository, productv2EntityMapper);
    }

    @Bean
    Productv2EntityMapper productv2EntityMapper() {
        return new Productv2EntityMapper();
    }

    @Bean
    Productv2DTOMapper productv2DTOMapper() {
        return new Productv2DTOMapper();
    }
}
