package com.snackbar.product.infrastructure;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.snackbar.product.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends MongoRepository<Product, String> {
    List<Product> getProductsByCategory(String category);
    Optional<Product> findByName(String name);
}