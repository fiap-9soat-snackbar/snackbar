package com.snackbar.cooking.application.usecases;

import com.snackbar.cooking.application.gateways.CookingGateway;
import com.snackbar.cooking.domain.entity.Cooking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReceiveOrderInteractor implements ReceiveOrderUseCase {

    private final CookingGateway cookingGateway;

    @Autowired
    public ReceiveOrderInteractor(CookingGateway cookingGateway) {
        this.cookingGateway = cookingGateway;
    }

    @Override
    public String execute(String id) {
        Cooking cooking = cookingGateway.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cooking not found"));

        if ("PAGO".equals(cooking.getStatusOrder())) {
            cooking.setStatusOrder("RECEBIDO");
            cookingGateway.save(cooking);
            return "Pedido recebido";
        }

        return "The cooking is already in " + cooking.getStatusOrder() + " status";
    }
}