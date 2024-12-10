package com.snackbar.checkout.usecase;

import com.snackbar.checkout.entity.Checkout;
import com.snackbar.checkout.gateway.CheckoutRepository;
import com.snackbar.order.service.OrderService;
import org.springframework.stereotype.Component;

@Component
public class CheckoutOrderUseCaseImpl implements CheckoutOrderUseCase {

    private final CheckoutRepository checkoutRepository;
    private final OrderService orderService;

    public CheckoutOrderUseCaseImpl(CheckoutRepository checkoutRepository, OrderService orderService) {
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

        // Atualizar o status da ordem
        orderService.updateStatusOrder(orderId);
    }
}
