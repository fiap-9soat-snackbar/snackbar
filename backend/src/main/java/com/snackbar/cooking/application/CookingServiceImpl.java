package com.snackbar.cooking.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.snackbar.cooking.application.usecase.*;
import com.snackbar.cooking.entity.Cooking;
import com.snackbar.cooking.gateway.CookingRepository;

@Service
public class CookingServiceImpl implements CookingService {

    private final CookingRepository cookingRepository;
    private final ReceiveOrderUseCase receiveOrderUseCase;
    private final StartPreparationUseCase startPreparationUseCase;
    private final FinishPreparationUseCase finishPreparationUseCase;

    @Autowired
    public CookingServiceImpl(CookingRepository cookingRepository) {
        this.cookingRepository = cookingRepository;
        this.receiveOrderUseCase = new ReceiveOrderUseCase(cookingRepository);
        this.startPreparationUseCase = new StartPreparationUseCase(cookingRepository);
        this.finishPreparationUseCase = new FinishPreparationUseCase(cookingRepository);
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
        return receiveOrderUseCase.execute(id);
    }

    @Override
    public String startPreparation(String id) {
        return startPreparationUseCase.execute(id);
    }

    @Override
    public String finishPreparation(String id) {
        return finishPreparationUseCase.execute(id);
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
