package com.snackbar.pickup.adapter.in.web;

import com.snackbar.pickup.service.PickupService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pickup")
public class PickupController {

    private final PickupService pickupService;

    public PickupController(PickupService pickupService) {
        this.pickupService = pickupService;
    }

    @PostMapping("/notificar/{orderId}")
    public String notificarCliente(@PathVariable String orderId) {
        pickupService.notificar(orderId);
        return "Cliente notificado para o pedido " + orderId;
    }

    @PostMapping("/confirmar/{orderId}")
    public String confirmarRetirada(@PathVariable String orderId) {
        pickupService.confirmar(orderId);
        return "Retirada confirmada para o pedido " + orderId;
    }
}
