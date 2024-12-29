package com.snackbar.product.usecase.impl;

import com.snackbar.product.dto.ProductDTO;
import com.snackbar.product.entity.Product;
import com.snackbar.product.gateway.ProductRepository;
import com.snackbar.product.usecase.UpdateProductUseCase;
import com.snackbar.product.common.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateProductUseCaseImpl implements UpdateProductUseCase {
    private final ProductRepository ProductRepository;

    @Autowired
    public UpdateProductUseCaseImpl(ProductRepository ProductRepository) {
        this.ProductRepository = ProductRepository;
    }

    @Override
    public ProductDTO updateProduct(String id, ProductDTO productDTO) {
        Product existingProduct = ProductRepository.findById(id)
            .orElseThrow(() -> new BusinessException("Product not found with id: " + id));

        Product updatedProduct = mapToEntity(productDTO);
        updatedProduct.setId(existingProduct.getId());
        updatedProduct.validateProduct();
        
        Product savedProduct = ProductRepository.save(updatedProduct);
        return mapToDTO(savedProduct);
    }

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