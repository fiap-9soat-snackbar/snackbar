package com.snackbar.cooking.application.usecase.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.snackbar.cooking.application.usecase.ReceiveOrderUseCase;
import com.snackbar.cooking.entity.Cooking;
import com.snackbar.cooking.gateway.CookingRepository;

@Component
public class ReceiveOrderUseCaseImpl implements ReceiveOrderUseCase {

    private final CookingRepository cookingRepository;

    @Autowired
    public ReceiveOrderUseCaseImpl(CookingRepository cookingRepository) {
        this.cookingRepository = cookingRepository;
    }

    @Override
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
