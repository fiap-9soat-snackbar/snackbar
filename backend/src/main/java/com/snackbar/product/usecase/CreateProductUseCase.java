package com.snackbar.product.usecase;

import com.snackbar.product.dto.ProductDTO;

public interface CreateProductUseCase {
    ProductDTO createProduct(ProductDTO productDTO);
}