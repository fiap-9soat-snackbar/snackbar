package com.snackbar.cooking.core.usecases;

import com.snackbar.cooking.core.domain.entities.Order;
import com.snackbar.cooking.core.domain.repositories.OrderRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderManagementUseCaseImpl implements OrderManagementUseCase {
    private final OrderRepository orderRepository;

    public OrderManagementUseCaseImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderById(String id) {
        return orderRepository.findById(id);
    }

    @Override
    public Order getOrderByNumber(String orderNumber) {
        return orderRepository.findByOrderNumber(orderNumber);
    }

    @Override
    public String startOrderPreparation(String id) {
        Order order = orderRepository.findById(id);
        if (order != null) {
            order.setStatusOrder("IN_PREPARATION");
            orderRepository.save(order);
            return "Order preparation started";
        }
        return "Order not found";
    }

    @Override
    public String finishOrderPreparation(String id) {
        Order order = orderRepository.findById(id);
        if (order != null) {
            order.setStatusOrder("READY");
            orderRepository.save(order);
            return "Order is ready";
        }
        return "Order not found";
    }

    @Override
    public List<Order> findOrdersByStatus(String statusOrder) {
        return orderRepository.findByStatusOrder(statusOrder);
    }

    @Override
    public void updateOrder(Order order) {
        orderRepository.save(order);
    }
}