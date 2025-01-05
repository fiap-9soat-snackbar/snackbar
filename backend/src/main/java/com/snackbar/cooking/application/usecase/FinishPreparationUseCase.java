package com.snackbar.cooking.application.usecase;

import com.snackbar.cooking.entity.Cooking;
import com.snackbar.cooking.gateway.CookingRepository;

public class FinishPreparationUseCase {
    private final CookingRepository cookingRepository;

    public FinishPreparationUseCase(CookingRepository cookingRepository) {
        this.cookingRepository = cookingRepository;
    }

    public String execute(String id) {
        Cooking cooking = cookingRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Cooking not found"));

        if ("PREPARACAO".equals(cooking.getStatusOrder())) {
            cooking.setStatusOrder("PRONTO");
            cookingRepository.save(cooking);
            return "Preparo pronto";
        }

        return "The cooking is currently in " + cooking.getStatusOrder() + " status";
    }
}
