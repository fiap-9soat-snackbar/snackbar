package com.snackbar.cooking.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.snackbar.cooking.domain.Cooking;
import com.snackbar.cooking.infrastructure.CookingRepository;
//import com.snackbar.cooking.application.CookingService;

@Service
public class CookingServiceImpl implements CookingService {


    @Autowired
    private CookingRepository cookingRepository;

    @Override
    public Cooking getById(String id) {
        return this.cookingRepository
        .findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Cooking not found"));
    }

    @Override
    public Cooking getByOrderNumber(String orderNumber) {
        return this.cookingRepository.findByOrderNumber(orderNumber);
    }

    public String startPreparation(String id) {
        Cooking cooking = cookingRepository.findById(id).orElseThrow(() -> new RuntimeException("Cooking not found"));
        if ("pending".equals(cooking.getStatusOrder())) {
            cooking.setStatusOrder("cooking");
            cookingRepository.save(cooking);
            return "Cooking status changed to 'cooking'";
        } else {
            return "The cooking is already in " + cooking.getStatusOrder() + " status";
        }
    }

    public String finishPreparation(String id) {
        Cooking cooking = cookingRepository.findById(id).orElseThrow(() -> new RuntimeException("Cooking not found"));
        if ("cooking".equals(cooking.getStatusOrder())) {
            cooking.setStatusOrder("ready");
            cookingRepository.save(cooking);
            return "Cooking status changed to 'ready'";
        } else {
            return "The cooking is currently in " + cooking.getStatusOrder() + " status";
        }
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
