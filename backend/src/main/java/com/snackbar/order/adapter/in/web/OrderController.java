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
    public Order criarOrder(@RequestBody Order order) {
        return orderService.criarOrder(order);
    }

    // Update order 
    @PutMapping
    public Order atualizarOrder(@RequestBody Order order) {
        return orderService.atualizarOrder(order);
    }

    // Search all orders
    @GetMapping
    public List<Order> listarTodosOrders() {
        return orderService.listarTodosOrders();
    }

    // Search Order with status (Checkout/Pickup)
    @GetMapping("/{id}")
    public Order buscarOrderComStatus(@PathVariable String id) {
        return orderService.buscarOrderComStatus(id);
    }
}
