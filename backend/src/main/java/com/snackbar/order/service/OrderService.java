package com.snackbar.order.service;


import com.snackbar.order.domain.model.Order;
import com.snackbar.order.domain.model.StatusOrder;
import com.snackbar.order.adapter.out.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // Create new order
    public Order createOrder(Order order) {
        order.setStatusOrder(StatusOrder.NOVO);
        return orderRepository.save(order);
    }

    // Apdate existent order
    public Order updateOrder(Order order) {
        return orderRepository.save(order);
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
