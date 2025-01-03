package com.snackbar.basket.usecase;

import com.snackbar.basket.entity.Basket;
import com.snackbar.basket.entity.Item;

public interface BasketOrderUseCase {
    void createBasket(Basket basket);
    Basket findBasket(String basketId);
    Basket addItemToBasket(String basketId, Item orderId);
}
