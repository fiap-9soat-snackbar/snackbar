package com.snackbar.product.usecase.impl;

import com.snackbar.product.dto.ProductDTO;
import com.snackbar.product.entity.Product;
import com.snackbar.product.gateway.ProductRepository;
import com.snackbar.product.usecase.GetProductUseCase;
import com.snackbar.product.common.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetProductUseCaseImpl implements GetProductUseCase {
    private final ProductRepository ProductRepository;

    @Autowired
    public GetProductUseCaseImpl(ProductRepository ProductRepository) {
        this.ProductRepository = ProductRepository;
    }

    @Override
    public ProductDTO getProduct(String id) {
        Product product = ProductRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Product not found"));
        return mapToDTO(product);
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