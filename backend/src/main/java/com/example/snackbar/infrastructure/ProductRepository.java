package com.example.snackbar.infrastructure;

import com.example.snackbar.domain.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {
    List<Product> getProductsByCategory(String category);
}