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

    @PostMapping("/notify/{orderId}")
    public String customerNotify(@PathVariable String orderId) {
        pickupService.notify(orderId);
        return "Customer notified for order " + orderId;
    }

    @PostMapping("/delivery/{orderId}")
    public String deliveryOrder(@PathVariable String orderId) {
        pickupService.delivery(orderId);
        return "Order " + orderId + " is Delivered";
    }
}
