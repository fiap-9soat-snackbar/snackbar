package com.snackbar.product.usecase.impl;

import com.snackbar.product.dto.ProductDTO;
import com.snackbar.product.entity.Product;
import com.snackbar.product.gateway.ProductRepository;
import com.snackbar.product.usecase.CreateProductUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateProductUseCaseImpl implements CreateProductUseCase {
    private final ProductRepository ProductRepository;

    @Autowired
    public CreateProductUseCaseImpl(ProductRepository ProductRepository) {
        this.ProductRepository = ProductRepository;
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = mapToEntity(productDTO);
        product.validateProduct();
        Product savedProduct = ProductRepository.save(product);
        return mapToDTO(savedProduct);
    }

    // Input transformation (ProductDTO to Product entity)
    private Product mapToEntity(ProductDTO productDTO) {
        return new Product(
                productDTO.getId(),
                productDTO.getName(),
                productDTO.getCategory(),
                productDTO.getDescription(),
                productDTO.getPrice(),
                productDTO.getCookingTime()
        );
    }
    
    // Output transformation (Product entity to ProductDTO)
    private ProductDTO mapToDTO(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getCategory(),
                product.getDescription(),
                product.getPrice(),
                product.getCookingTime()
        );
    }
}