package com.snackbar.product.usecase;

import com.snackbar.product.dto.ProductDTO;

public interface GetProductUseCase {
    ProductDTO getProduct(String id);
}