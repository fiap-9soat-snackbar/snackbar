package com.snackbar.checkout.usecase;

import com.snackbar.basket.application.usecases.BasketUseCase;
import com.snackbar.basket.domain.entity.Basket;
import com.snackbar.checkout.entity.Checkout;
import com.snackbar.checkout.gateway.CheckoutRepository;
import com.snackbar.order.domain.model.Order;
import com.snackbar.order.domain.model.StatusOrder;
import com.snackbar.order.service.OrderService;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CheckoutOrderUseCaseImpl implements CheckoutOrderUseCase {

    private final CheckoutRepository checkoutRepository;
    private final OrderService orderService;
    private final BasketUseCase basketUseCase;

    public CheckoutOrderUseCaseImpl(CheckoutRepository checkoutRepository, OrderService orderService, BasketUseCase basketUseCase) {
        this.checkoutRepository = checkoutRepository;
        this.orderService = orderService;
        this.basketUseCase = basketUseCase;
    }

    @Override
    public void pay(String basketId) {
        Basket basket = basketUseCase.findBasket(basketId);

        Order order = new Order();
        order.setName(basket.name());
        order.setCpf(basket.cpf());
        order.setStatusOrder(StatusOrder.NOVO);
        order.setTotalPrice(basket.totalPrice());

        order.setItems(basket.items().stream().map(item -> {
            com.snackbar.order.domain.model.Item orderItem = new com.snackbar.order.domain.model.Item();
            orderItem.setName(item.name());
            orderItem.setQuantity(item.quantity());
            return orderItem;
        }).collect(Collectors.toList()));

        basketUseCase.createBasket(order);

//
//        Order orderCreated = this.orderService.createOrder(order);
//
//        Checkout checkout = new Checkout();
//        checkout.setOrderId(orderCreated.getId());
//        checkout.setPaymentMethod("Mercado Pago");
//        checkoutRepository.save(checkout);

//         Atualizar o status da ordem
//        orderService.updateStatusOrder(orderId);
    }
}
