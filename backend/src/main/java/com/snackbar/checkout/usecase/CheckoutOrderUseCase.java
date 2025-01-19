package com.snackbar.checkout.usecase;

public interface CheckoutOrderUseCase {
    void pay(String orderId);
    boolean isPaid(String orderId);
}
