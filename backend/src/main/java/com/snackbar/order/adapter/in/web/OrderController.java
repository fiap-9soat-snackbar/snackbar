package com.snackbar.order.adapter.in.web;

import com.snackbar.order.domain.model.Order;
import com.snackbar.order.service.OrderService;
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
        return orderService.listOrders();
    }

    // Search Order with status
    @GetMapping("/{id}")
    public Order searchOrderWithStatus(@PathVariable String id) {
        return orderService.searchOrderWithStatus(id);
    }
}
