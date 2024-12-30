package com.snackbar.basket.presentation;

import com.snackbar.checkout.usecase.CheckoutOrderUseCase;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/checkout")
public class BasketController {

    private final CheckoutOrderUseCase checkoutOrderUseCase;

    public BasketController(CheckoutOrderUseCase checkoutOrderUseCase) {
        this.checkoutOrderUseCase = checkoutOrderUseCase;
    }
    @PostMapping("/{orderId}")
    public String makePayment(@PathVariable String orderId) {
        checkoutOrderUseCase.pay(orderId);
        return "Pagamento do pedido " + orderId + " pago via Mercado Pago (QR Code) com sucesso!";
    }
}
