package com.snackbar.cooking.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.snackbar.cooking.entity.Cooking;
import com.snackbar.cooking.gateway.CookingRepository;

@Service
public class CookingServiceImpl implements CookingService {

    private final CookingRepository cookingRepository;

    @Autowired
    public CookingServiceImpl(CookingRepository cookingRepository) {
        this.cookingRepository = cookingRepository;
    }

    @Override
    public Cooking getById(String id) {
        return cookingRepository
            .findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Cooking not found"));
    }

    @Override
    public Cooking getByOrderNumber(String orderNumber) {
        return cookingRepository.findByOrderNumber(orderNumber);
    }

    @Override
    public String receiveOrder(String id) {
        Cooking cooking = getById(id);
        if ("PAGO".equals(cooking.getStatusOrder())) {
            cooking.setStatusOrder("RECEBIDO");
            updateOrder(cooking);
            return "Pedido recebido";
        }
        return "The cooking is already in " + cooking.getStatusOrder() + " status";
    }

    @Override
    public String startPreparation(String id) {
        Cooking cooking = getById(id);
        if ("RECEBIDO".equals(cooking.getStatusOrder())) {
            cooking.setStatusOrder("PREPARACAO");
            updateOrder(cooking);
            return "Preparo iniciado";
        }
        return "The cooking is already in " + cooking.getStatusOrder() + " status";
    }

    @Override
    public String finishPreparation(String id) {
        Cooking cooking = getById(id);
        if ("PREPARACAO".equals(cooking.getStatusOrder())) {
            cooking.setStatusOrder("PRONTO");
            updateOrder(cooking);
            return "Preparo pronto";
        }
        return "The cooking is currently in " + cooking.getStatusOrder() + " status";
    }

    @Override
    public void updateOrder(Cooking cooking) {
        cookingRepository.save(cooking);
    }

    @Override
    public List<Cooking> findByStatuses(List<String> statuses) {
        return cookingRepository.findByStatusOrderIn(statuses);
    }

    @Override
    public List<Cooking> obtainAll(List<String> statuses) {
        return findByStatuses(statuses);
    }
}
