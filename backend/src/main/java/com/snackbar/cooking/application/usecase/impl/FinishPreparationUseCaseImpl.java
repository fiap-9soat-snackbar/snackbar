package com.snackbar.cooking.application.usecase.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.snackbar.cooking.application.usecase.FinishPreparationUseCase;
import com.snackbar.cooking.entity.Cooking;
import com.snackbar.cooking.gateway.CookingRepository;

@Component
public class FinishPreparationUseCaseImpl implements FinishPreparationUseCase {

    private final CookingRepository cookingRepository;

    @Autowired
    public FinishPreparationUseCaseImpl(CookingRepository cookingRepository) {
        this.cookingRepository = cookingRepository;
    }

    @Override
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
