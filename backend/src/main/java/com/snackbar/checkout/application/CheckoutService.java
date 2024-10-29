package com.snackbar.checkout.application;

import com.snackbar.checkout.domain.model.Checkout;
import com.snackbar.checkout.adapter.out.CheckoutRepository;
import org.springframework.stereotype.Service;

@Service
public class CheckoutService implements CheckoutOrderUseCase {

    private final CheckoutRepository checkoutRepository;

    public CheckoutService(CheckoutRepository checkoutRepository) {
        this.checkoutRepository = checkoutRepository;
    }

    @Override
    public void pay(String orderId) {
        Checkout checkout = new Checkout();
        checkout.setOrderId(orderId);
        checkout.setPaid(true);
        checkoutRepository.save(checkout);
    }
}
