package com.example.snackbar.infrastructure;

import com.example.snackbar.domain.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}