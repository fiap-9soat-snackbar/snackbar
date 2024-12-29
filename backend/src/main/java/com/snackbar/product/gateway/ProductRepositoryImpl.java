package com.snackbar.product.gateway;

import com.snackbar.product.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepositoryImpl extends MongoRepository<Product, String>, ProductRepository {
    List<Product> findByCategory(String category);
    Optional<Product> findByName(String name);
}