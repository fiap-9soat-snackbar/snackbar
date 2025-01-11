package com.snackbar.productv2.infrastructure.gateways;

import com.snackbar.productv2.domain.entity.Productv2;
import com.snackbar.productv2.infrastructure.persistence.Productv2Entity;

public class Productv2EntityMapper {
    Productv2Entity toEntity(Productv2 productv2DomainObj) {
        return new Productv2Entity(productv2DomainObj.id (), productv2DomainObj.name (), productv2DomainObj.category(), productv2DomainObj.description(), productv2DomainObj.price(), productv2DomainObj.cookingTime());
    }
    
    Productv2 toDomainObj(Productv2Entity productv2Entity) {
        return new Productv2(productv2Entity.getId(), productv2Entity.getName(), productv2Entity.getCategory(), productv2Entity.getDescription(), productv2Entity.getPrice(), productv2Entity.getCookingTime());
    }
}
