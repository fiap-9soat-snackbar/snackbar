package com.snackbar.productv2.infrastructure.gateways;

// This should be equivalent to the previous ProductRepository

import com.snackbar.productv2.application.gateways.Productv2Gateway;
import com.snackbar.productv2.domain.entity.Productv2;
import com.snackbar.productv2.infrastructure.persistence.Productv2Entity;
import com.snackbar.productv2.infrastructure.persistence.Productv2Repository;

public class Productv2RepositoryGateway implements Productv2Gateway {

    private final Productv2Repository productv2Repository;
    private final Productv2EntityMapper productv2EntityMapper;

    public Productv2RepositoryGateway(Productv2Repository productv2Repository, Productv2EntityMapper productv2EntityMapper) {
        this.productv2Repository = productv2Repository;
        this.productv2EntityMapper = productv2EntityMapper;
    }

    @Override
    public Productv2 createProductv2(Productv2 productv2DomainObj) {
        Productv2Entity productv2Entity = productv2EntityMapper.toEntity(productv2DomainObj);
        Productv2Entity savedObj = productv2Repository.save(productv2Entity);
        Productv2 createdProductv2 = productv2EntityMapper.toDomainObj(savedObj);
        return createdProductv2;
    }
    
    @Override
    public Productv2 getProductv2(Productv2 productv2DomainObj) {
        Productv2Entity productv2Entity = productv2EntityMapper.toEntity(productv2DomainObj);
        Productv2Entity retrievedObj = productv2Repository.findById(productv2Entity.getId()).orElse(null);
        Productv2 retrievedProductv2 = productv2EntityMapper.toDomainObj(retrievedObj);
        return retrievedProductv2;
    }

}
