package com.example.snackbar.application;

import com.example.snackbar.domain.Product;
import java.util.List;

public interface ProductService {
    Product createProduct(Product product);
    Product getProduct(String id);
    List<Product> getAllProducts();
    Product updateProduct(String id, Product product);
    void deleteProduct(String id);
}