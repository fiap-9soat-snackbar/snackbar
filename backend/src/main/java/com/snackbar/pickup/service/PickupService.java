package com.snackbar.pickup.service;

import com.snackbar.pickup.adapter.out.PickupRepository;
import com.snackbar.pickup.domain.model.Pickup;
import com.snackbar.pickup.domain.model.StatusPickup;
import com.snackbar.pickup.application.*;
import com.snackbar.order.service.OrderService;
import org.springframework.stereotype.Service;


@Service
public class PickupService implements DeliveryPickupUseCase, NotifyCustomerUseCase {

    private final PickupRepository pickupRepository;
    private final OrderService orderService;

    public PickupService(PickupRepository pickupRepository, OrderService orderService) {
        this.pickupRepository = pickupRepository;
        this.orderService = orderService;
    }

    @Override
    public void notify(String orderId) {
        Pickup pickup = new Pickup();
        pickup.setOrderId(orderId);
        pickup.setStatusPickup(StatusPickup.PRONTO);
        pickup.setNotifyCustomer(true);

        // Update  StatusOders (Checkout Collection)
        pickupRepository.save(pickup);
        System.out.println("Pedido " + orderId + "está PRONTO, Cliente notificado");

        // Update  StatusOders (Order Collection)
        orderService.updateStatusOrder(orderId);
    }

    @Override
    public void delivery(String orderId) {
        // Search Pickup associate to OrderID on Database (MongoDB)
        Pickup pickup = pickupRepository.findByOrderId(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Este pedido não foi retirado: " + orderId));

        pickup.setStatusPickup(StatusPickup.FINALIZADO);

       // Update  StatusOders (Pickup Collection)
        pickupRepository.save(pickup);
        System.out.println("Order " + orderId + " is delivered");

        // Update  StatusOders (Order Collection)
        orderService.updateStatusOrder(orderId);
    }
}
