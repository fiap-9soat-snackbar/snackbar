package com.snackbar.productv2.infrastructure.persistence;

// This should be equivalent to the previous ProductRepositoryImpl, therefore actually having 
// dependencies to a specific framework or library - in this case both Spring and MongoDB.

import com.snackbar.productv2.domain.entity.Product;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends MongoRepository<ProductEntity, String> {
    List<Product> findByCategory(String category);
    Optional<Product> findByName(String name);
}
