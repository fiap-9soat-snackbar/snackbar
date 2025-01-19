package com.snackbar.cooking.application.usecases;

import com.snackbar.cooking.domain.entity.Cooking;
import com.snackbar.cooking.application.gateways.CookingGateway;
import org.springframework.stereotype.Component;

@Component
public class FindCookingByIdUseCase {
    private final CookingGateway cookingGateway;

    public FindCookingByIdUseCase(CookingGateway cookingGateway) {
        this.cookingGateway = cookingGateway;
    }

    public Cooking execute(String orderId) {
        return cookingGateway.findByOrderId(orderId);
    }
}