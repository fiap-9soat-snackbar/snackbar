package com.snackbar.checkout.application;

import com.snackbar.checkout.domain.model.Checkout;
import com.snackbar.checkout.adapter.out.CheckoutRepository;
import org.springframework.stereotype.Service;

@Service
public class CheckoutService implements PagarOrderUseCase {

    private final CheckoutRepository checkoutRepository;

    public CheckoutService(CheckoutRepository checkoutRepository) {
        this.checkoutRepository = checkoutRepository;
    }

    @Override
    public void pagar(String orderId) {
        Checkout checkout = new Checkout();
        checkout.setOrderId(orderId);
        checkout.setPago(true);
        checkoutRepository.save(checkout);
    }
}
