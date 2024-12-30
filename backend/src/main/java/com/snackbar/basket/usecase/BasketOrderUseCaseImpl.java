package com.snackbar.basket.usecase;

import com.snackbar.basket.entity.Basket;
import com.snackbar.basket.gateway.BasketRepository;
import com.snackbar.order.service.OrderService;
import org.springframework.stereotype.Component;

@Component
public class BasketOrderUseCaseImpl implements BasketOrderUseCase {

    private final BasketRepository basketRepository;
    private final OrderService orderService;

    public BasketOrderUseCaseImpl(BasketRepository basketRepository, OrderService orderService) {
        this.basketRepository = basketRepository;
        this.orderService = orderService;
    }

    @Override
    public void pay(String orderId) {
        Basket basket = new Basket();
        basket.setOrderId(orderId);
        basket.setPaid(true);
        basket.setPaymentMethod("Mercado Pago");
        basketRepository.save(basket);

        // Atualizar o status da ordem
        orderService.updateStatusOrder(orderId);
    }
}
