package com.snackbar.product.usecase.impl;

import com.snackbar.product.entity.Product;
import com.snackbar.product.gateway.ProductGateway;
import com.snackbar.product.usecase.DeleteProductUseCase;
import com.snackbar.product.common.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteProductUseCaseImpl implements DeleteProductUseCase {
    private final ProductGateway productGateway;

    @Autowired
    public DeleteProductUseCaseImpl(ProductGateway productGateway) {
        this.productGateway = productGateway;
    }

    @Override
    public void deleteProduct(String id) {
        Product product = productGateway.findById(id)
                .orElseThrow(() -> new BusinessException("Product not found with id: " + id));
        
        String productName = product.getName();
        
        productGateway.deleteById(id);
        
        System.out.println("Product with ID: " + id + " and Name: " + productName + " has been successfully deleted.");
    }

}