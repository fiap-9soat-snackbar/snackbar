package com.snackbar.cooking.application.usecase.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.snackbar.cooking.application.usecase.StartPreparationUseCase;
import com.snackbar.cooking.entity.Cooking;
import com.snackbar.cooking.gateway.CookingRepository;

@Component
public class StartPreparationUseCaseImpl implements StartPreparationUseCase {

    private final CookingRepository cookingRepository;

    @Autowired
    public StartPreparationUseCaseImpl(CookingRepository cookingRepository) {
        this.cookingRepository = cookingRepository;
    }

    @Override
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
