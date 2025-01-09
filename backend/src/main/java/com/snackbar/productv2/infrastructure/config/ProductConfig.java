package com.snackbar.productv2.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.snackbar.productv2.application.gateways.ProductGateway;
import com.snackbar.productv2.application.usecases.CreateProductUseCase;
import com.snackbar.productv2.infrastructure.controllers.ProductDTOMapper;
import com.snackbar.productv2.infrastructure.gateways.ProductEntityMapper;
import com.snackbar.productv2.infrastructure.gateways.ProductRepositoryGateway;
import com.snackbar.productv2.infrastructure.persistence.ProductRepository;

@Configuration
public class ProductConfig {
    @Bean
    CreateProductUseCase createProductUseCase(ProductGateway productGateway) {
        return new CreateProductUseCase(productGateway);
    }
    
    @Bean
    ProductGateway productGateway(ProductRepository productRepository, ProductEntityMapper productEntityMapper) {
        return new ProductRepositoryGateway(productRepository, productEntityMapper);
    }

    @Bean
    ProductEntityMapper productEntityMapper() {
        return new ProductEntityMapper();
    }

    @Bean
    ProductDTOMapper productDTOMapper() {
        return new ProductDTOMapper();
    }
}
