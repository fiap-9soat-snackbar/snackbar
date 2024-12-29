package com.snackbar.product.usecase;

import com.snackbar.product.dto.ProductDTO;

public interface UpdateProductUseCase {
    ProductDTO updateProduct(String id, ProductDTO productDTO);
}