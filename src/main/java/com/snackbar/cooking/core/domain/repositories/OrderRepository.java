package com.snackbar.cooking.core.domain.repositories;

import com.snackbar.cooking.core.domain.entities.Order;
import java.util.List;

public interface OrderRepository {
    List<Order> findAll();
    Order findById(String id);
    Order findByOrderNumber(String orderNumber);
    List<Order> findByStatusOrder(String statusOrder);
    void save(Order order);
}