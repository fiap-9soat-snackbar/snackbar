package com.snackbar.pickup.service;

import com.snackbar.pickup.adapter.out.PickupRepository;
import com.snackbar.pickup.domain.model.Pickup;
import com.snackbar.pickup.domain.model.StatusPickup;
import com.snackbar.order.service.OrderService;
import org.springframework.stereotype.Service;

@Service
public class PickupService {

    private final PickupRepository pickupRepository;
    private final OrderService orderService;

    public PickupService(PickupRepository pickupRepository, OrderService orderService) {
        this.pickupRepository = pickupRepository;
        this.orderService = orderService;
    }

    public void notifyCustomer(String orderId) {
        Pickup pickup = new Pickup();
        pickup.setOrderId(orderId);
        pickup.setStatusPickup(StatusPickup.PRONTO);
        pickup.setNotifyCustomer(true);

        pickupRepository.save(pickup);
        System.out.println("Pedido " + orderId + " está PRONTO, Cliente notificado");

        orderService.updateStatusOrder(orderId);
    }

    public void deliveryOrder(String orderId) {

        Pickup pickup = pickupRepository.findByOrderId(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Este pedido não foi retirado: " + orderId));

        pickup.setStatusPickup(StatusPickup.FINALIZADO);

        pickupRepository.save(pickup);
        System.out.println("Pedido " + orderId + " foi entregue");

        orderService.updateStatusOrder(orderId);
    }
}
