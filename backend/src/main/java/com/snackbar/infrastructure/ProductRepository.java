package com.snackbar.infrastructure;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.snackbar.domain.Product;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {
    List<Product> getProductsByCategory(String category);
}