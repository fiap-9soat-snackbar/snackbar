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
    public Order criarOrder(Order order) {
        order.setStatusOrder(StatusOrder.NOVO);
        return orderRepository.save(order);
    }

    // Apdate existent order
    public Order atualizarOrder(Order order) {
        return orderRepository.save(order);
    }

    // List all order
    public List<Order> listarTodosOrders() {
        return orderRepository.findAll();
    }

    // Search order with status (Checkout/Pickup)
    public Order buscarOrderComStatus(String orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado"));

        // Request Checkout Status
        boolean isPago = checkoutRepository.findByOrderId(orderId)
                .map(Checkout::isPago)
                .orElse(false);
        order.setPago(isPago);

        // Request Pickup Status
        boolean isRetirado = pickupRepository.findByOrderId(orderId)
                .map(pickup -> pickup.getStatusPickup() == StatusPickup.RETIRADO)
                .orElse(false);
        order.setRetirado(isRetirado);

        return order;
    }
}
