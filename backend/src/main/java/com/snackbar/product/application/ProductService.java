package com.snackbar.product.application;

import com.snackbar.product.domain.Product;
import java.util.List;

public interface ProductService {
    Product createProduct(Product product);
    Product getProduct(String id);
    Product getProductByName(String name);
    List<Product> getAllProducts();
    List<Product> getProductsByCategory(String category);
    Product updateProduct(String id, Product product);
    void deleteProduct(String id);
}