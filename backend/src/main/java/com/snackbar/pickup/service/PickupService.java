package com.snackbar.pickup.service;

import com.snackbar.pickup.adapter.out.PickupRepository;
import com.snackbar.pickup.domain.model.Pickup;
import com.snackbar.pickup.domain.model.StatusPickup;
import com.snackbar.pickup.application.*;
import org.springframework.stereotype.Service;

@Service
public class PickupService implements ConfirmarPickupUseCase, NotificarClienteUseCase {

    private final PickupRepository pickupRepository;

    public PickupService(PickupRepository pickupRepository) {
        this.pickupRepository = pickupRepository;
    }

    @Override
    public void notificar(String orderId) {
        Pickup pickup = new Pickup();
        pickup.setOrderId(orderId);
        pickup.setStatusPickup(StatusPickup.PRONTO);
        pickup.setClienteNotificado(true);

        // Save status on Database (MongoDB)
        pickupRepository.save(pickup);
        System.out.println("Notificação enviada para o pedido " + orderId);
    }

    @Override
    public void confirmar(String orderId) {
        // Search Pickup associate to OrderID on Database (MongoDB)
        Pickup pickup = pickupRepository.findByOrderId(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Retirada não encontrada para o pedido " + orderId));

        pickup.setStatusPickup(StatusPickup.RETIRADO);

        // Update status on Database (MongoDB)
        pickupRepository.save(pickup);
        System.out.println("Pedido " + orderId + " retirado.");
    }
}
