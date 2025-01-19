package com.snackbar.cooking.application.usecases;

import com.snackbar.cooking.domain.entity.Cooking;
import com.snackbar.cooking.domain.exceptions.CookingOperationException;
import com.snackbar.cooking.domain.exceptions.OrderStatusInvalidException;
import com.snackbar.cooking.domain.exceptions.OrderUpdateException;
import com.snackbar.order.domain.model.Order;
import com.snackbar.order.domain.model.StatusOrder;
import com.snackbar.cooking.application.gateways.CookingGateway;
import com.snackbar.order.service.OrderService;
import com.snackbar.cooking.infrastructure.persistence.CookingEntity;
import com.snackbar.cooking.infrastructure.persistence.CookingRepository;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

@Service
public class StartPreparationUseCase {

    private final CookingGateway cookingGateway;
    private final CookingRepository cookingRepository;
    private final OrderService orderService;

    public StartPreparationUseCase(CookingGateway cookingGateway, CookingRepository cookingRepository, OrderService orderService) {
        this.cookingGateway = cookingGateway;
        this.cookingRepository = cookingRepository;
        this.orderService = orderService;
    }

    public Cooking updateCooking(Cooking cooking) {
        // 1. Get and validate order
        Order order = orderService.searchOrderId(cooking.orderId());
        validateOrderStatus(order);
        
        try {
            // 2. Create cooking record with RECEBIDO status
            System.out.println("cooking.orderId(): " +  cooking.orderId());
            cookingGateway.updateCookingStatus(cooking.orderId(), StatusOrder.PREPARACAO);
            Cooking savedCooking = cookingGateway.findByOrderId(cooking.orderId());
            // 3. Update order status
            System.out.println("Before");
            orderService.updateOrderStatus(savedCooking.id(), StatusOrder.PREPARACAO);

            return savedCooking;
            
        } catch (Exception e) {
            throw new CookingOperationException("Failed to process cooking order", e);
        }
    }

    private void validateOrderStatus(Order order) {
        if (order.getStatusOrder() != StatusOrder.RECEBIDO) {
            throw new OrderStatusInvalidException(
                "Order must be in RECEBIDO status to be received for cooking. Current status: " + order.getStatusOrder()
            );
        }
    }

    private void updateOrderStatus(Order order) {
        try {
            order.setStatusOrder(StatusOrder.PREPARACAO);
            orderService.updateOrder(order);
        } catch (Exception e) {
            throw new OrderUpdateException("Failed to update order status", e);
        }
    }
}