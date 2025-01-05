package com.snackbar.product.usecase;

import com.snackbar.product.dto.ProductDTO;
import java.util.List;

public interface ProductUseCase {
    ProductDTO createProduct(ProductDTO productDTO);
    ProductDTO getProduct(String id);
    ProductDTO getProductByName(String name);
    List<ProductDTO> getAllProducts();
    List<ProductDTO> getProductsByCategory(String category);
    ProductDTO updateProduct(String id, ProductDTO productDTO);
    void deleteProduct(String id);
}