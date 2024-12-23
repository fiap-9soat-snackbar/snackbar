package com.snackbar.cooking.application.usecase;

import com.snackbar.cooking.entity.Cooking;
import com.snackbar.cooking.gateway.CookingRepository;

public class StartPreparationUseCase {
    private final CookingRepository cookingRepository;

    public StartPreparationUseCase(CookingRepository cookingRepository) {
        this.cookingRepository = cookingRepository;
    }

    public String execute(String id) {
        Cooking cooking = cookingRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Cooking not found"));

        if ("RECEBIDO".equals(cooking.getStatusOrder())) {
            cooking.setStatusOrder("PREPARACAO");
            cookingRepository.save(cooking);
            return "Preparo iniciado";
        }

        return "The cooking is already in " + cooking.getStatusOrder() + " status";
    }
}
