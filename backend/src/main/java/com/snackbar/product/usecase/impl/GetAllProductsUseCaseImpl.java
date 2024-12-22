package com.snackbar.product.usecase.impl;

import com.snackbar.product.dto.ProductDTO;
import com.snackbar.product.entity.Product;
import com.snackbar.product.gateway.ProductGateway;
import com.snackbar.product.usecase.GetAllProductsUseCase;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetAllProductsUseCaseImpl implements GetAllProductsUseCase {
    private final ProductGateway productGateway;

    @Autowired
    public GetAllProductsUseCaseImpl(ProductGateway productGateway) {
        this.productGateway = productGateway;
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return productGateway.findAll().stream()
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