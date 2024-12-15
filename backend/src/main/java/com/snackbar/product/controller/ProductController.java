package com.snackbar.product.controller;

import com.snackbar.product.common.controller.BaseController;
import com.snackbar.product.common.dto.ApiResponse;
import com.snackbar.product.dto.ProductDTO;
import com.snackbar.product.usecase.ProductUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController extends BaseController {
    private final ProductUseCase productUseCase;

    @Autowired
    public ProductController(ProductUseCase productUseCase) {
        this.productUseCase = productUseCase;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ProductDTO>> createProduct(@RequestBody ProductDTO productDTO) {
        ProductDTO createdProduct = productUseCase.createProduct(productDTO);
        return success(createdProduct, "Product created successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductDTO>> getProduct(@PathVariable String id) {
        ProductDTO product = productUseCase.getProduct(id);
        return success(product, "Product retrieved successfully");
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductDTO>>> getAllProducts() {
        List<ProductDTO> products = productUseCase.getAllProducts();
        return success(products, "Products retrieved successfully");
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<ApiResponse<List<ProductDTO>>> getProductsByCategory(@PathVariable String category) {
        List<ProductDTO> products = productUseCase.getProductsByCategory(category);
        return success(products, "Products retrieved successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductDTO>> updateProduct(@PathVariable String id, @RequestBody ProductDTO productDTO) {
        ProductDTO updatedProduct = productUseCase.updateProduct(id, productDTO);
        return success(updatedProduct, "Product updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteProduct(@PathVariable String id) {
        productUseCase.deleteProduct(id);
        return success(null, "Product deleted successfully");
    }
}