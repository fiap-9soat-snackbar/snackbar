package com.snackbar.pickup.usecase;

import com.snackbar.pickup.entity.Pickup;
import com.snackbar.pickup.entity.StatusPickup;
import com.snackbar.pickup.gateway.PickupRepository;
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
        // Search for Pickup associated with OrderID
        Pickup pickup = pickupRepository.findByOrderId(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Este pedido não foi retirado: " + orderId));

        // Update status to FINALIZADO
        pickup.setStatusPickup(StatusPickup.FINALIZADO);

        // Save status update in Pickup Collection
        pickupRepository.save(pickup);
        System.out.println("Pedido " + orderId + " foi Finalizado");

        // Update status in Order Collection
        orderService.updateStatusOrder(orderId);
    }
}
