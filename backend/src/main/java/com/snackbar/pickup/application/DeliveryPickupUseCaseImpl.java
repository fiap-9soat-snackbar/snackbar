package com.snackbar.pickup.application;

import com.snackbar.pickup.adapter.out.PickupRepository;
import com.snackbar.pickup.domain.model.Pickup;
import com.snackbar.pickup.domain.model.StatusPickup;
import com.snackbar.order.service.OrderService;

import org.springframework.stereotype.Service;

@Service
public class DeliveryPickupUseCaseImpl implements DeliveryPickupUseCase {

    private final PickupRepository pickupRepository;
    private final OrderService orderService;

    public DeliveryPickupUseCaseImpl(PickupRepository pickupRepository, OrderService orderService) {
        this.pickupRepository = pickupRepository;
        this.orderService = orderService;
    }

    @Override
    public void delivery(String orderId) {
        Pickup pickup = pickupRepository.findByOrderId(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Este pedido não foi retirado: " + orderId));

        pickup.setStatusPickup(StatusPickup.FINALIZADO);
        pickupRepository.save(pickup);
        orderService.updateStatusOrder(orderId);
    }
}
