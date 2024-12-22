package com.snackbar.order.adapter.in.web;

import com.snackbar.order.domain.model.Order;
import com.snackbar.order.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // Create New order
    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        if (order.getCpf() == null) {
            throw new IllegalArgumentException("CPF is mandatory for creating an order");
        }
        return orderService.createOrder(order);
    }

    // Update order 
    @PutMapping
    public Order updateOrder(@RequestBody Order order) {
        return orderService.updateOrder(order);
    }

    // Search all orders
    @GetMapping
    public List<Order> listOrders() {
        List<Order> orders = orderService.listOrders();
        orders.forEach(Order::calculateRemainingTime);
        return orders;
    }

    // Search Order with status
    @GetMapping("/{id}")
    public Order searchOrderWithStatus(@PathVariable String id) {
        Order order = orderService.searchOrderWithStatus(id);
        return order;
    }

    // Search Order by orderNumber
    @GetMapping("/number/{orderNumber}")
    public ResponseEntity<?> getOrderByOrderNumber(@PathVariable String orderNumber) {
        try {
            Order order = orderService.getOrderByOrderNumber(orderNumber);
            return ResponseEntity.ok(order);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    //Search sorted orders
    @GetMapping("/sorted")
    public List<Order> getSortedOrders() {
        return orderService.getSortedOrders();
    }
}
