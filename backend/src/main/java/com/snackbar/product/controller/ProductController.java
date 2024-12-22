package com.snackbar.product.controller;

import com.snackbar.product.common.controller.BaseController;
import com.snackbar.product.common.dto.ApiResponse;
import com.snackbar.product.dto.ProductDTO;
import com.snackbar.product.usecase.CreateProductUseCase;
import com.snackbar.product.usecase.GetProductUseCase;
import com.snackbar.product.usecase.GetAllProductsUseCase;
import com.snackbar.product.usecase.GetProductsByCategoryUseCase;
import com.snackbar.product.usecase.UpdateProductUseCase;
import com.snackbar.product.usecase.DeleteProductUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController extends BaseController {
    private final CreateProductUseCase createProductUseCase;
    private final GetProductUseCase getProductUseCase;
    private final GetAllProductsUseCase getAllProductsUseCase;
    private final GetProductsByCategoryUseCase getProductsByCategoryUseCase;
    private final UpdateProductUseCase updateProductUseCase;
    private final DeleteProductUseCase deleteProductUseCase;

    @Autowired
    public ProductController(
            CreateProductUseCase createProductUseCase,
            GetProductUseCase getProductUseCase,
            GetAllProductsUseCase getAllProductsUseCase,
            GetProductsByCategoryUseCase getProductsByCategoryUseCase,
            UpdateProductUseCase updateProductUseCase,
            DeleteProductUseCase deleteProductUseCase) {
        this.createProductUseCase = createProductUseCase;
        this.getProductUseCase = getProductUseCase;
        this.getAllProductsUseCase = getAllProductsUseCase;
        this.getProductsByCategoryUseCase = getProductsByCategoryUseCase;
        this.updateProductUseCase = updateProductUseCase;
        this.deleteProductUseCase = deleteProductUseCase;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ProductDTO>> createProduct(@RequestBody ProductDTO productDTO) {
        ProductDTO createdProduct = createProductUseCase.createProduct(productDTO);
        return success(createdProduct, "Product created successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductDTO>> getProduct(@PathVariable String id) {
        ProductDTO product = getProductUseCase.getProduct(id);
        return success(product, "Product retrieved successfully");
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductDTO>>> getAllProducts() {
        List<ProductDTO> products = getAllProductsUseCase.getAllProducts();
        return success(products, "Products retrieved successfully");
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<ApiResponse<List<ProductDTO>>> getProductsByCategory(@PathVariable String category) {
        List<ProductDTO> products = getProductsByCategoryUseCase.getProductsByCategory(category);
        return success(products, "Products retrieved successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductDTO>> updateProduct(@PathVariable String id, @RequestBody ProductDTO productDTO) {
        ProductDTO updatedProduct = updateProductUseCase.updateProduct(id, productDTO);
        return success(updatedProduct, "Product updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteProduct(@PathVariable String id) {
        deleteProductUseCase.deleteProduct(id);
        return success(null, "Product deleted successfully");
    }
}