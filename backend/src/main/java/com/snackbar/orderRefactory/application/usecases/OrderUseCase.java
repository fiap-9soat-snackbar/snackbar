package com.snackbar.orderRefactory.application.usecases;

import com.snackbar.orderRefactory.application.gateways.ProductGateway;
import com.snackbar.orderRefactory.domain.entity.Order;
import com.snackbar.orderRefactory.domain.entity.OrderItem;
import com.snackbar.orderRefactory.domain.valueobject.StatusOrder;
import com.snackbar.orderRefactory.application.gateways.OrderGateway;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

public class OrderUseCase {

    private final OrderGateway orderGateway;
    private final ProductGateway productGateway;

    public OrderUseCase(OrderGateway orderGateway, ProductGateway productGateway) {
        this.orderGateway = orderGateway;
        this.productGateway = productGateway;
    }

    public Order createOrder(Order order) {
        if (order.cpf() == null) {
            throw new IllegalArgumentException("CPF is mandatory for creating an order");
        }

        // Validate if the user exists and get the user's name
        String userName = orderGateway.findUserByCpf(order.cpf())
                .orElseThrow(() -> new IllegalArgumentException("User with provided CPF does not exist"));

        order = new Order(order.id(), order.orderNumber(), order.orderDateTime(), order.cpf(), userName, order.items(), order.statusOrder(), order.paymentMethod(), order.totalPrice(), order.remainingTime());

        String lastOrderNumber = orderGateway.findTopByOrderByOrderNumberDesc();

        order = new Order(order.id(), Order.generateOrderNumber(lastOrderNumber), order.orderDateTime(), order.cpf(), order.name(), order.items(), StatusOrder.NOVO, order.paymentMethod(), order.totalPrice(), order.remainingTime());
        order = new Order(order.id(), order.orderNumber(), Instant.now(), order.cpf(), order.name(), order.items(), order.statusOrder(), order.paymentMethod(), order.totalPrice(), order.remainingTime());

        List<OrderItem> updatedItems = order.items().stream().map(item -> {
            OrderItem updatedItem = productGateway.getProductByName(item.name());
            if (updatedItem != null) {
                return new OrderItem(item.id(), updatedItem.name(), item.quantity(), updatedItem.price(), updatedItem.cookingTime(), updatedItem.customization());
            } else {
                throw new IllegalArgumentException("Product not found: " + item.name());
            }
        }).collect(Collectors.toList());

        order = new Order(order.id(), order.orderNumber(), order.orderDateTime(), order.cpf(), order.name(), updatedItems, order.statusOrder(), order.paymentMethod(), order.totalPrice(), order.remainingTime());

        BigDecimal totalPrice = updatedItems.stream()
                .map(item -> item.price() != null ? item.price().multiply(BigDecimal.valueOf(item.quantity())) : BigDecimal.ZERO)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        order = new Order(order.id(), order.orderNumber(), order.orderDateTime(), order.cpf(), order.name(), order.items(), order.statusOrder(), order.paymentMethod(), totalPrice, order.remainingTime());

        return orderGateway.createOrder(order);
    }

    public Order updateOrder(Order order) {
        Order existingOrder = orderGateway.findOrderById(order.id())
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        List<OrderItem> updatedItems = order.items().stream().map(item -> {
            OrderItem updatedItem = productGateway.getProductByName(item.name());
            if (updatedItem != null) {
                return new OrderItem(item.id(), updatedItem.name(), item.quantity(), updatedItem.price(), updatedItem.cookingTime(), updatedItem.customization());
            } else {
                throw new IllegalArgumentException("Product not found: " + item.name());
            }
        }).collect(Collectors.toList());

        existingOrder = new Order(existingOrder.id(), existingOrder.orderNumber(), existingOrder.orderDateTime(), existingOrder.cpf(), existingOrder.name(), updatedItems, existingOrder.statusOrder(), existingOrder.paymentMethod(), existingOrder.totalPrice(), existingOrder.remainingTime());

        BigDecimal totalPrice = updatedItems.stream()
                .map(item -> item.price() != null ? item.price().multiply(BigDecimal.valueOf(item.quantity())) : BigDecimal.ZERO)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        existingOrder = new Order(existingOrder.id(), existingOrder.orderNumber(), existingOrder.orderDateTime(), existingOrder.cpf(), existingOrder.name(), existingOrder.items(), existingOrder.statusOrder(), existingOrder.paymentMethod(), totalPrice, existingOrder.remainingTime());

        return orderGateway.updateOrder(existingOrder);
    }

    public List<Order> listOrders() {
        return orderGateway.listOrders();
    }

    public Order searchOrderId(String orderId) {
        return orderGateway.searchOrderId(orderId);
    }

    public void updateStatusOrder(String orderId) {

    }

    public Order getOrderByOrderNumber(String orderNumber) {
        return orderGateway.getOrderByOrderNumber(orderNumber);
    }

    public List<Order> getSortedOrders() {
        return orderGateway.getSortedOrders();
    }
}
