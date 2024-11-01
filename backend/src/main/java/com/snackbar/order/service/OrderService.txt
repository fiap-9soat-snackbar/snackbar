package com.snackbar.order.service;

import com.snackbar.order.domain.model.Order;
import com.snackbar.order.domain.model.StatusOrder;
import com.snackbar.order.domain.model.Item;
import com.snackbar.order.adapter.out.OrderRepository;
import com.snackbar.product.application.ProductService;
import com.snackbar.product.domain.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.math.BigDecimal;

@Service
public class OrderService { // Service for managing orders

    private final OrderRepository orderRepository;
    private final ProductService productService;

    public OrderService(OrderRepository orderRepository, ProductService productService) {
        this.orderRepository = orderRepository;
        this.productService = productService;
    }

    // Create new order
    public Order createOrder(Order order) {
        String lastOrderNumber = orderRepository.findTopByOrderByOrderNumberDesc()
                .map(Order::getOrderNumber)
                .orElse(null);
        order.setOrderNumber(Order.generateOrderNumber(lastOrderNumber));
        order.setStatusOrder(StatusOrder.NOVO);
        
        List<Item> updatedItems = order.getItems().stream().map(item -> {
            Product product = productService.getProductByName(item.getName());
            if (product != null) {
                item.setName(product.getName());  // Explicitly set the product name
                item.setPrice(product.getPrice());
                item.setCookingTime(product.getCookingTime());
            } else {
                throw new IllegalArgumentException("Product not found: " + item.getName());
            }
            return item;
        }).collect(Collectors.toList());
        
        order.setItems(updatedItems);
        
        // Calculate and set the total price
        BigDecimal totalPrice = updatedItems.stream()
            .map(item -> item.getPrice() != null ? item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())) : BigDecimal.ZERO)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        order.setTotalPrice(totalPrice);
        
        // No need to set waiting time as it's now calculated dynamically in Order class
        
        return orderRepository.save(order);
    }

    // Update existing order
    public Order updateOrder(Order order) {
        // Fetch the existing order
        Order existingOrder = orderRepository.findById(order.getId())
            .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        // Update items
        List<Item> updatedItems = order.getItems().stream().map(item -> {
            Product product = productService.getProductByName(item.getName());
            if (product != null) {
                item.setName(product.getName());
                item.setPrice(product.getPrice());
                item.setCookingTime(product.getCookingTime());
            } else {
                throw new IllegalArgumentException("Product not found: " + item.getName());
            }
            return item;
        }).collect(Collectors.toList());
        
        existingOrder.setItems(updatedItems);

        // Calculate and set the total price
        BigDecimal totalPrice = updatedItems.stream()
            .map(item -> item.getPrice() != null ? item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())) : BigDecimal.ZERO)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        existingOrder.setTotalPrice(totalPrice);

        // No need to set waiting time as it's now calculated dynamically in Order class

        return orderRepository.save(existingOrder);
    }

    // List all order
    public List<Order> listOrders() {
        return orderRepository.findAll();
    }

    // Search order with status
    public Order searchOrderWithStatus(String orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not Found"));
        return order;
    }
}
