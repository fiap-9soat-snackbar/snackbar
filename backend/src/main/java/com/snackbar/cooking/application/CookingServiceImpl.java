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
    public String startPreparation(String id) {
        Cooking cooking = getById(id);
        if ("pending".equals(cooking.getStatusOrder())) {
            cooking.setStatusOrder("cooking");
            updateOrder(cooking);
            return "Cooking status changed to 'cooking'";
        }
        return "The cooking is already in " + cooking.getStatusOrder() + " status";
    }

    @Override
    public String finishPreparation(String id) {
        Cooking cooking = getById(id);
        if ("cooking".equals(cooking.getStatusOrder())) {
            cooking.setStatusOrder("ready");
            updateOrder(cooking);
            return "Cooking status changed to 'ready'";
        }
        return "The cooking is currently in " + cooking.getStatusOrder() + " status";
    }

    @Override
    public void updateOrder(Cooking cooking) {
        cookingRepository.save(cooking);
    }

    @Override
    public List<Cooking> findByStatusOrder(String statusOrder) {
        return cookingRepository.findByStatusOrder(statusOrder);
    }

    @Override
    public List<Cooking> obtainAll() {
        return findByStatusOrder("PREPARACAO");
    }
}
