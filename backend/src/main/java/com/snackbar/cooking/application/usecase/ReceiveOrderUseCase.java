package com.snackbar.cooking.application.usecase;

import com.snackbar.cooking.entity.Cooking;
import com.snackbar.cooking.gateway.CookingRepository;

public class ReceiveOrderUseCase {
    private final CookingRepository cookingRepository;

    public ReceiveOrderUseCase(CookingRepository cookingRepository) {
        this.cookingRepository = cookingRepository;
    }

    public String execute(String id) {
        Cooking cooking = cookingRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Cooking not found"));

        if ("PAGO".equals(cooking.getStatusOrder())) {
            cooking.setStatusOrder("RECEBIDO");
            cookingRepository.save(cooking);
            return "Pedido recebido";
        }

        return "The cooking is already in " + cooking.getStatusOrder() + " status";
    }
}
