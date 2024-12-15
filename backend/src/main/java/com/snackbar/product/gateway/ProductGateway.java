package com.snackbar.product.gateway;

import com.snackbar.product.entity.Product;
import java.util.List;
import java.util.Optional;

public interface ProductGateway {
    Product save(Product product);
    Optional<Product> findById(String id);
    Optional<Product> findByName(String name);
    List<Product> findAll();
    List<Product> findByCategory(String category);
    boolean existsById(String id);
    void deleteById(String id);
}