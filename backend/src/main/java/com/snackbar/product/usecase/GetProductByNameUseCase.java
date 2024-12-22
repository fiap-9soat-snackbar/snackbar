package com.snackbar.product.usecase;

import com.snackbar.product.dto.ProductDTO;

public interface GetProductByNameUseCase {
    ProductDTO getProductByName(String name);
}