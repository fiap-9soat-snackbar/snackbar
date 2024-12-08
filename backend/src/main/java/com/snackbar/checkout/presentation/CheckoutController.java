package com.snackbar.checkout.presentation;

import com.snackbar.checkout.usecase.CheckoutOrderUseCase;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {

    private final CheckoutOrderUseCase checkoutOrderUseCase;

    public CheckoutController(CheckoutOrderUseCase checkoutOrderUseCase) {
        this.checkoutOrderUseCase = checkoutOrderUseCase;
    }
    @PostMapping("/{orderId}")
    public String makePayment(@PathVariable String orderId) {
        checkoutOrderUseCase.pay(orderId);
        return "Pagamento do pedido " + orderId + " pago via Mercado Pago (QR Code) com sucesso!";
    }
}
