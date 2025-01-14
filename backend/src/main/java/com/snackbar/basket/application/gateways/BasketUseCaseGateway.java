package com.snackbar.basket.application.gateways;

import com.snackbar.basket.domain.entity.*;

public interface BasketUseCaseGateway {
    Basket createBasket(Basket basket);
    Basket findBasket(String basketId);
    Basket addItemToBasket(String basketId, Item orderId);
    Basket deleteItemToBasket(String basketId, String itemId);
}
