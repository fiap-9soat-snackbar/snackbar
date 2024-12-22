package com.snackbar.product.usecase;

import com.snackbar.product.dto.ProductDTO;
import java.util.List;

public interface GetProductsByCategoryUseCase {
    List<ProductDTO> getProductsByCategory(String category);
}