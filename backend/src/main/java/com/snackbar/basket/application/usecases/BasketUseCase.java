package com.snackbar.basket.application.usecases;

import com.snackbar.basket.application.gateways.BasketUseCaseGateway;
import com.snackbar.basket.domain.entity.Basket;
import com.snackbar.basket.domain.entity.Item;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

public class BasketUseCase {

    private final BasketUseCaseGateway basketUseCaseGateway;

    public BasketUseCase(BasketUseCaseGateway basketUseCaseGateway) {
        this.basketUseCaseGateway = basketUseCaseGateway;
    }

    public Basket createBasket(Basket basket) {
        BigDecimal totalPrice = basket.items().stream()
                .map(item -> item.price().multiply(BigDecimal.valueOf(item.quantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        Basket updatedBasket = new Basket(
                basket.id(),
                basket.basketDateTime(),
                basket.cpf(),
                basket.name(),
                basket.items(),
                totalPrice
        );
        return basketUseCaseGateway.createBasket(updatedBasket);
    }

    public Basket findBasket(String basketId) {
        return basketUseCaseGateway.findBasket(basketId);
    }

    public Basket addItemToBasket(String basketId, Item item) {
        return basketUseCaseGateway.addItemToBasket(basketId, item);
    }

    public Basket deleteItemToBasket(String basketId, String name) {
        return basketUseCaseGateway.deleteItemToBasket(basketId, name);
    }

    public List<Basket> findAllBaskets() {
        return basketUseCaseGateway.findAllBaskets();
    }
}
