package com.snackbar.productv2.infrastructure.gateways;

// This should be equivalent to the previous ProductRepository

import com.snackbar.productv2.application.gateways.ProductGateway;
import com.snackbar.productv2.domain.entity.Product;
import com.snackbar.productv2.infrastructure.persistence.ProductEntity;
import com.snackbar.productv2.infrastructure.persistence.ProductRepository;

public class ProductRepositoryGateway implements ProductGateway {

    private final ProductRepository productRepository;
    private final ProductEntityMapper productEntityMapper;

    public ProductRepositoryGateway(ProductRepository productRepository, ProductEntityMapper productEntityMapper) {
        this.productRepository = productRepository;
        this.productEntityMapper = productEntityMapper;
    }

    @Override
    public Product createProduct(Product productDomainObj) {
        ProductEntity productEntity = productEntityMapper.toEntity(productDomainObj);
        ProductEntity savedObj = productRepository.save(productEntity);
        Product createdProduct = productEntityMapper.toDomainObj(savedObj);
        return createdProduct;
    }
    
    @Override
    public Product getProduct(Product productDomainObj) {
        ProductEntity productEntity = productEntityMapper.toEntity(productDomainObj);
        ProductEntity retrievedObj = productRepository.findById(productEntity.getId()).orElse(null);
        Product retrievedProduct = productEntityMapper.toDomainObj(retrievedObj);
        return retrievedProduct;
    }

}
