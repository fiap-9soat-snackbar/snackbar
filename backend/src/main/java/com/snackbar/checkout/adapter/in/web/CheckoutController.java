package com.snackbar.checkout.adapter.in.web;

import org.springframework.web.bind.annotation.*;

import com.snackbar.checkout.service.CheckoutService;

@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {

    private final CheckoutService checkoutService;

    public CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @PostMapping("/{orderId}")
    public String makePayment(@PathVariable String orderId) {
        checkoutService.pay(orderId);
        return "Payment made successfully for the order " + orderId;
    }
}
