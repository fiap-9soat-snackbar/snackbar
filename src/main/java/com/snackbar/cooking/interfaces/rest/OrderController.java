package com.snackbar.cooking.interfaces.rest;

import com.snackbar.cooking.core.domain.entities.Order;
import com.snackbar.cooking.core.usecases.OrderManagementUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderManagementUseCase orderManagementUseCase;

    public OrderController(OrderManagementUseCase orderManagementUseCase) {
        this.orderManagementUseCase = orderManagementUseCase;
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderManagementUseCase.getAllOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable String id) {
        Order order = orderManagementUseCase.getOrderById(id);
        return order != null ? ResponseEntity.ok(order) : ResponseEntity.notFound().build();
    }

    @GetMapping("/number/{orderNumber}")
    public ResponseEntity<Order> getOrderByNumber(@PathVariable String orderNumber) {
        Order order = orderManagementUseCase.getOrderByNumber(orderNumber);
        return order != null ? ResponseEntity.ok(order) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/start-preparation")
    public ResponseEntity<String> startPreparation(@PathVariable String id) {
        String result = orderManagementUseCase.startOrderPreparation(id);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}/finish-preparation")
    public ResponseEntity<String> finishPreparation(@PathVariable String id) {
        String result = orderManagementUseCase.finishOrderPreparation(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/status/{statusOrder}")
    public ResponseEntity<List<Order>> getOrdersByStatus(@PathVariable String statusOrder) {
        return ResponseEntity.ok(orderManagementUseCase.findOrdersByStatus(statusOrder));
    }
}