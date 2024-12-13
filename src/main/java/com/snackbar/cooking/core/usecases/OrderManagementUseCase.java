package com.snackbar.cooking.core.usecases;

import com.snackbar.cooking.core.domain.entities.Order;
import java.util.List;

public interface OrderManagementUseCase {
    List<Order> getAllOrders();
    Order getOrderById(String id);
    Order getOrderByNumber(String orderNumber);
    String startOrderPreparation(String id);
    String finishOrderPreparation(String id);
    List<Order> findOrdersByStatus(String statusOrder);
    void updateOrder(Order order);
}