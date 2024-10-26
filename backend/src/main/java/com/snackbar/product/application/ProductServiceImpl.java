package com.snackbar.product.application;

import com.snackbar.product.domain.Product;
import com.snackbar.product.infrastructure.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.math.BigDecimal;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product createProduct(Product product) {
        validateProduct(product);
        return productRepository.save(product);
    }

    private void validateProduct(Product product) {
        if (product.getName() == null || product.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Product name is required");
        }
        if (product.getCategory() == null || product.getCategory().trim().isEmpty()) {
            throw new IllegalArgumentException("Product category is required");
        }
        if (product.getDescription() == null || product.getDescription().trim().isEmpty()) {
            throw new IllegalArgumentException("Product description is required");
        }
        if (Objects.isNull(product.getPrice())) {
            throw new IllegalArgumentException("Product price is required");
        }
        if (product.getPrice().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Product price must be zero or greater");
        }
    }

    @Override
    public Product getProduct(String id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return productRepository.getProductsByCategory(category);
    }

    @Override
    public Product updateProduct(String id, Product product) {
        Product existingProduct = getProduct(id);
        if (product.getName() != null && !product.getName().trim().isEmpty()) {
            existingProduct.setName(product.getName());
        }
        if (product.getCategory() != null && !product.getCategory().trim().isEmpty()) {
            existingProduct.setCategory(product.getCategory());
        }
        if (product.getDescription() != null && !product.getDescription().trim().isEmpty()) {
            existingProduct.setDescription(product.getDescription());
        }
        if (product.getPrice() != null && product.getPrice().compareTo(BigDecimal.ZERO) >= 0) {
            existingProduct.setPrice(product.getPrice());
        }
        return productRepository.save(existingProduct);
    }

    @Override
    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }
}