package com.snackbar.pickup.application;

import com.snackbar.pickup.adapter.out.PickupRepository;
import com.snackbar.pickup.domain.model.Pickup;
import com.snackbar.pickup.domain.model.StatusPickup;
import com.snackbar.order.service.OrderService;

import org.springframework.stereotype.Service;

@Service
public class NotifyCustomerUseCaseImpl implements NotifyCustomerUseCase {

    private final PickupRepository pickupRepository;
    private final OrderService orderService;

    public NotifyCustomerUseCaseImpl(PickupRepository pickupRepository, OrderService orderService) {
        this.pickupRepository = pickupRepository;
        this.orderService = orderService;
    }

    @Override
    public void notify(String orderId) {
        Pickup pickup = new Pickup();
        pickup.setOrderId(orderId);
        pickup.setStatusPickup(StatusPickup.PRONTO);
        pickup.setNotifyCustomer(true);

        pickupRepository.save(pickup);
        orderService.updateStatusOrder(orderId);
    }
}
