package com.snackbar.product.controller;

import com.snackbar.product.dto.ProductDTO;
import com.snackbar.product.usecase.ProductUseCase;
import com.snackbar.common.dto.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.badRequest;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductUseCase productUseCase;

    @Autowired
    public ProductController(ProductUseCase productUseCase) {
        this.productUseCase = productUseCase;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ProductDTO>> createProduct(@RequestBody ProductDTO productDTO) {
        try {
            ProductDTO createdProduct = productUseCase.createProduct(productDTO);
            return ok(new ApiResponse<>(true, "Product created successfully", createdProduct));
        } catch (Exception e) {
            return badRequest().body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductDTO>> getProduct(@PathVariable String id) {
        try {
            ProductDTO product = productUseCase.getProduct(id);
            return ok(new ApiResponse<>(true, "Product retrieved successfully", product));
        } catch (Exception e) {
            return badRequest().body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductDTO>>> getAllProducts() {
        try {
            List<ProductDTO> products = productUseCase.getAllProducts();
            return ok(new ApiResponse<>(true, "Products retrieved successfully", products));
        } catch (Exception e) {
            return badRequest().body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<ApiResponse<List<ProductDTO>>> getProductsByCategory(@PathVariable String category) {
        try {
            List<ProductDTO> products = productUseCase.getProductsByCategory(category);
            return ok(new ApiResponse<>(true, "Products retrieved successfully", products));
        } catch (Exception e) {
            return badRequest().body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductDTO>> updateProduct(@PathVariable String id, @RequestBody ProductDTO productDTO) {
        try {
            ProductDTO updatedProduct = productUseCase.updateProduct(id, productDTO);
            return ok(new ApiResponse<>(true, "Product updated successfully", updatedProduct));
        } catch (Exception e) {
            return badRequest().body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteProduct(@PathVariable String id) {
        try {
            productUseCase.deleteProduct(id);
            return ok(new ApiResponse<>(true, "Product deleted successfully", null));
        } catch (Exception e) {
            return badRequest().body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }
}