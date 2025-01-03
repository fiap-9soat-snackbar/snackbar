package com.snackbar.basket.usecase;

import com.snackbar.basket.entity.Basket;
import com.snackbar.basket.entity.Item;
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
    public void createBasket(Basket basket) {

    }

    @Override
    public Basket findBasket(String basketId) {
        return null;
    }

    @Override
    public Basket addItemToBasket(String basketId, Item orderId) {
        return null;
    }
}
