package com.snackbar.orderRefactory.application.gateways;

import com.snackbar.orderRefactory.domain.entity.OrderItem;

public interface ProductGateway {
    OrderItem getProductByName(String name);
}
