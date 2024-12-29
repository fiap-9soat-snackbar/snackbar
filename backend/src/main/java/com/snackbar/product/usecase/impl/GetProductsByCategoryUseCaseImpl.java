package com.snackbar.product.usecase.impl;

import com.snackbar.product.dto.ProductDTO;
import com.snackbar.product.entity.Product;
import com.snackbar.product.gateway.ProductRepository;
import com.snackbar.product.usecase.GetProductsByCategoryUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class GetProductsByCategoryUseCaseImpl implements GetProductsByCategoryUseCase {
    private final ProductRepository ProductRepository;

    @Autowired
    public GetProductsByCategoryUseCaseImpl(ProductRepository ProductRepository) {
        this.ProductRepository = ProductRepository;
    }

    @Override
    public List<ProductDTO> getProductsByCategory(String category) {
        return ProductRepository.findByCategory(category).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
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