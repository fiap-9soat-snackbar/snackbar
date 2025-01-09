package com.snackbar.orderRefactory.infrastructure.controllers;

import com.snackbar.orderRefactory.domain.entity.Order;
import com.snackbar.orderRefactory.domain.entity.OrderItem;

import java.util.List;
import java.util.stream.Collectors;

public class OrderDTOMapper {


    public Order toDomain(CreateOrderRequest request) {
        List<OrderItem> items = request.items().stream()
                .map(item -> new OrderItem(
                        item.productId(),
                        null, // Add appropriate value for the second argument
                        item.quantity(),
                        item.price(),
                        null, // Add appropriate value for the fifth argument
                        null  // Add appropriate value for the sixth argument
                ))
                .collect(Collectors.toList());
        return new Order(
                null, // Add appropriate value for the first argument
                request.customerId(),
                request.orderDate(),
                null, // Add appropriate value for the fourth argument
                null, // Add appropriate value for the fifth argument
                items,
                null, // Add appropriate value for the seventh argument
                request.totalPrice().toString(), // Convert BigDecimal to String
                null, // Add appropriate value for the ninth argument
                0  // Add appropriate value for the tenth argument
        );
    }

    public CreateOrderResponse toResponse(Order order) {
        List<OrderItemResponse> items = order.items().stream()
                .map(item -> new OrderItemResponse(
                        item.id(),
                        item.quantity(),
                        item.price()
                ))
                .collect(Collectors.toList());
        return new CreateOrderResponse(
                order.id(),
                order.cpf(),
                order.orderDateTime(),
                items,
                order.totalPrice()
        );
    }
}
