package com.snackbar.cooking.application.usecases;

import com.snackbar.cooking.application.gateways.CookingGateway;
import com.snackbar.cooking.domain.entity.Cooking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StartPreparationInteractor implements StartPreparationUseCase {

    private final CookingGateway cookingGateway;

    @Autowired
    public StartPreparationInteractor(CookingGateway cookingGateway) {
        this.cookingGateway = cookingGateway;
    }

    @Override
    public String execute(String id) {
        Cooking cooking = cookingGateway.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cooking not found"));

        if ("RECEBIDO".equals(cooking.getStatusOrder())) {
            cooking.setStatusOrder("PREPARACAO");
            cookingGateway.save(cooking);
            return "Preparo iniciado";
        }

        return "The cooking is already in " + cooking.getStatusOrder() + " status";
    }
}