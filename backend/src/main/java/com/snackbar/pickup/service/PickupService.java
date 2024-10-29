package com.snackbar.pickup.service;

import com.snackbar.pickup.adapter.out.PickupRepository;
import com.snackbar.pickup.domain.model.Pickup;
import com.snackbar.pickup.domain.model.StatusPickup;
import com.snackbar.pickup.application.*;
import org.springframework.stereotype.Service;

@Service
public class PickupService implements DeliveryPickupUseCase, NotifyCustomerUseCase {

    private final PickupRepository pickupRepository;

    public PickupService(PickupRepository pickupRepository) {
        this.pickupRepository = pickupRepository;
    }

    @Override
    public void notify(String orderId) {
        Pickup pickup = new Pickup();
        pickup.setOrderId(orderId);
        pickup.setStatusPickup(StatusPickup.READY);
        pickup.setNotifyCustomer(true);

        // Save status on Database (MongoDB)
        pickupRepository.save(pickup);
        System.out.println("Order " + orderId + "is Ready, Notified Customer");
    }

    @Override
    public void delivery(String orderId) {
        // Search Pickup associate to OrderID on Database (MongoDB)
        Pickup pickup = pickupRepository.findByOrderId(orderId)
                .orElseThrow(() -> new IllegalArgumentException("This order has not yet been delivered" + orderId));

        pickup.setStatusPickup(StatusPickup.DELIVERED);

        // Update status on Database (MongoDB)
        pickupRepository.save(pickup);
        System.out.println("Order " + orderId + " is delivered");
    }
}
