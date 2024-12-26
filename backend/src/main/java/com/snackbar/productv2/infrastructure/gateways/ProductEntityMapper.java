package com.snackbar.productv2.infrastructure.gateways;

import com.snackbar.productv2.domain.entity.Product;
import com.snackbar.productv2.infrastructure.persistence.ProductEntity;

public class ProductEntityMapper {
    ProductEntity toEntity(Product productDomainObj) {
        return new ProductEntity(productDomainObj.name (), productDomainObj.category(), productDomainObj.description(), productDomainObj.price(), productDomainObj.cookingTime());
    }
    
    Product toDomainObj(ProductEntity productEntity) {
        return new Product(productEntity.getName(), productEntity.getCategory(), productEntity.getDescription(), productEntity.getPrice(), productEntity.getCookingTime());
    }
}
