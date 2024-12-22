package com.snackbar.product.usecase.impl;

import com.snackbar.product.dto.ProductDTO;
import com.snackbar.product.entity.Product;
import com.snackbar.product.gateway.ProductGateway;
import com.snackbar.product.usecase.GetProductByNameUseCase;
import com.snackbar.product.common.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetProductByNameUseCaseImpl implements GetProductByNameUseCase {
    private final ProductGateway productGateway;

    @Autowired
    public GetProductByNameUseCaseImpl(ProductGateway productGateway) {
        this.productGateway = productGateway;
    }

    @Override
    public ProductDTO getProductByName(String name) {
        Product product = productGateway.findByName(name)
            .orElseThrow(() -> new BusinessException("Product not found with name: " + name));
        return mapToDTO(product);
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