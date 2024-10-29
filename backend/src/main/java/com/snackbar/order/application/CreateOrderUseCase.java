package com.snackbar.order.application;

import com.snackbar.order.domain.model.Order;

public interface CreateOrderUseCase {
    Order createOrder(Order order);
}