package com.snackbar.cooking.usecase.impl;

import com.snackbar.cooking.domain.entity.CookingEntity;
import com.snackbar.cooking.usecase.port.CookingRepository;
import com.snackbar.cooking.usecase.port.CookingUseCase;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CookingUseCaseImpl implements CookingUseCase {
    private final CookingRepository cookingRepository;

    public CookingUseCaseImpl(CookingRepository cookingRepository) {
        this.cookingRepository = cookingRepository;
    }

    @Override
    public List<CookingEntity> getAllCookings() {
        return cookingRepository.findAll();
    }

    @Override
    public CookingEntity getById(String id) {
        return cookingRepository.findById(id);
    }

    @Override
    public CookingEntity getByOrderNumber(String orderNumber) {
        return cookingRepository.findByOrderNumber(orderNumber);
    }

    @Override
    public String startPreparation(String id) {
        CookingEntity cooking = getById(id);
        if (cooking != null) {
            cooking.setStatusOrder("IN_PREPARATION");
            cookingRepository.save(cooking);
            return "Order preparation started";
        }
        return "Order not found";
    }

    @Override
    public String finishPreparation(String id) {
        CookingEntity cooking = getById(id);
        if (cooking != null) {
            cooking.setStatusOrder("READY");
            cookingRepository.save(cooking);
            return "Order is ready";
        }
        return "Order not found";
    }

    @Override
    public List<CookingEntity> findByStatusOrder(String statusOrder) {
        return cookingRepository.findByStatusOrder(statusOrder);
    }

    @Override
    public void updateOrder(CookingEntity cooking) {
        cookingRepository.save(cooking);
    }
}