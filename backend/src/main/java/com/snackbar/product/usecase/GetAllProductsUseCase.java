package com.snackbar.product.usecase;

import java.util.List;

import com.snackbar.product.dto.ProductDTO;

public interface GetAllProductsUseCase {
    List<ProductDTO> getAllProducts();
}