package com.snackbar.checkout.service;

import com.snackbar.checkout.domain.model.Checkout;
import com.snackbar.checkout.adapter.out.CheckoutRepository;
import com.snackbar.checkout.application.CheckoutOrderUseCase;

import org.springframework.stereotype.Service;
import com.snackbar.order.service.OrderService;

@Service
public class CheckoutService implements CheckoutOrderUseCase {

    private final CheckoutRepository checkoutRepository;
    private final OrderService orderService;

    public CheckoutService(CheckoutRepository checkoutRepository, OrderService orderService) {
        this.checkoutRepository = checkoutRepository;
        this.orderService = orderService;
    }

    @Override
    public void pay(String orderId) {
        Checkout checkout = new Checkout();
        checkout.setOrderId(orderId);
        checkout.setPaid(true);
        checkout.setPaymentMethod("Mercado Pago");
        checkoutRepository.save(checkout);

        // Update  StatusOders (Order Collection)
        orderService.updateStatusOrder(orderId);
    }
}
