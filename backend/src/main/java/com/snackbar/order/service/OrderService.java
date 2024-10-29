package com.snackbar.order.service;


import com.snackbar.order.domain.model.Order;
import com.snackbar.order.domain.model.StatusOrder;
import com.snackbar.order.adapter.out.OrderRepository;
import com.snackbar.checkout.adapter.out.CheckoutRepository;
import com.snackbar.checkout.domain.model.Checkout;
import com.snackbar.pickup.adapter.out.PickupRepository;
import com.snackbar.pickup.domain.model.StatusPickup;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CheckoutRepository checkoutRepository;
    private final PickupRepository pickupRepository;

    public OrderService(OrderRepository orderRepository, CheckoutRepository checkoutRepository, PickupRepository pickupRepository) {
        this.orderRepository = orderRepository;
        this.checkoutRepository = checkoutRepository;
        this.pickupRepository = pickupRepository;
    }

    // Create new order
    public Order createOrder(Order order) {
        order.setStatusOrder(StatusOrder.NEW);
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

    // Search order with status (Checkout/Pickup)
    public Order searchOrderWithStatus(String orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not Found"));

        // Request Checkout Status
        boolean isPaid = checkoutRepository.findByOrderId(orderId)
                .map(Checkout::ispaid)
                .orElse(false);
        order.setPaid(isPaid);

        // Request Pickup Status
        boolean isDelivered = pickupRepository.findByOrderId(orderId)
                .map(pickup -> pickup.getStatusPickup() == StatusPickup.DELIVERED)
                .orElse(false);
        order.setDelivered(isDelivered);

        return order;
    }
}
