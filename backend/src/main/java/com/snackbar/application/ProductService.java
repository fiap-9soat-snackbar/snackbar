package com.snackbar.application;

import com.snackbar.domain.Product;
import java.util.List;

public interface ProductService {
    Product createProduct(Product product);
    Product getProduct(String id);
    List<Product> getAllProducts();
    List<Product> getProductsByCategory(String category);
    Product updateProduct(String id, Product product);
    void deleteProduct(String id);
}