package com.snackbar.checkout.adapter.in.web;

import com.snackbar.checkout.application.CheckoutService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {

    private final CheckoutService checkoutService;

    public CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @PostMapping("/{orderId}")
    public String realizarPagamento(@PathVariable String orderId) {
        checkoutService.pagar(orderId);
        return "Pagamento realizado com sucesso para o pedido " + orderId;
    }
}
