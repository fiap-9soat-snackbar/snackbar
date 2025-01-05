package com.snackbar.product.usecase;

import com.snackbar.product.dto.ProductDTO;
import com.snackbar.product.entity.Product;
import com.snackbar.product.gateway.ProductGateway;
import com.snackbar.product.common.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductUseCaseImpl implements ProductUseCase {
    private final ProductGateway productGateway;

    @Autowired
    public ProductUseCaseImpl(ProductGateway productGateway) {
        this.productGateway = productGateway;
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = mapToEntity(productDTO);
        product.validateProduct();
        Product savedProduct = productGateway.save(product);
        return mapToDTO(savedProduct);
    }

    @Override
    public ProductDTO getProduct(String id) {
        Product product = productGateway.findById(id)
            .orElseThrow(() -> new BusinessException("Product not found with id: " + id));
        return mapToDTO(product);
    }

    @Override
    public ProductDTO getProductByName(String name) {
        Product product = productGateway.findByName(name)
            .orElseThrow(() -> new BusinessException("Product not found with name: " + name));
        return mapToDTO(product);
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return productGateway.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> getProductsByCategory(String category) {
        return productGateway.findByCategory(category).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO updateProduct(String id, ProductDTO productDTO) {
        Product existingProduct = productGateway.findById(id)
            .orElseThrow(() -> new BusinessException("Product not found with id: " + id));

        Product updatedProduct = mapToEntity(productDTO);
        updatedProduct.setId(existingProduct.getId());
        updatedProduct.validateProduct();
        
        Product savedProduct = productGateway.save(updatedProduct);
        return mapToDTO(savedProduct);
    }

    @Override
    public void deleteProduct(String id) {
        if (!productGateway.existsById(id)) {
            throw new BusinessException("Product not found with id: " + id);
        }
        productGateway.deleteById(id);
    }

    private Product mapToEntity(ProductDTO productDTO) {
        return new Product(
                productDTO.getId(),
                productDTO.getName(),
                productDTO.getCategory(),
                productDTO.getDescription(),
                productDTO.getPrice(),
                productDTO.getCookingTime()
        );
    }

    private ProductDTO mapToDTO(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getCategory(),
                product.getDescription(),
                product.getPrice(),
                product.getCookingTime()
        );
    }
}