package com.snackbar.cooking.application.usecases;

import com.snackbar.cooking.application.gateways.CookingGateway;
import com.snackbar.cooking.domain.entity.Cooking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FinishPreparationInteractor implements FinishPreparationUseCase {

    private final CookingGateway cookingGateway;

    @Autowired
    public FinishPreparationInteractor(CookingGateway cookingGateway) {
        this.cookingGateway = cookingGateway;
    }

    @Override
    public String execute(String id) {
        Cooking cooking = cookingGateway.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cooking not found"));

        if ("PREPARACAO".equals(cooking.getStatusOrder())) {
            cooking.setStatusOrder("PRONTO");
            cookingGateway.save(cooking);
            return "Preparo pronto";
        }

        return "The cooking is currently in " + cooking.getStatusOrder() + " status";
    }
}