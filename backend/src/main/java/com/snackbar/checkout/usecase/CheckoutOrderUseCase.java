package com.snackbar.checkout.usecase;

public interface CheckoutOrderUseCase {
    void checkout(String orderId);
    boolean isPaid(String orderId);
}
