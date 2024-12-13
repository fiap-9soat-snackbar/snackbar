package com.snackbar.cooking.application.service;

import com.snackbar.cooking.application.port.input.CookingUseCase;
import com.snackbar.cooking.application.port.output.CookingPersistencePort;
import com.snackbar.cooking.domain.model.Cooking;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CookingServiceImpl implements CookingUseCase {

    private final CookingPersistencePort persistencePort;

    public CookingServiceImpl(CookingPersistencePort persistencePort) {
        this.persistencePort = persistencePort;
    }

    @Override
    public List<Cooking> getAllCookings() {
        return persistencePort.findAll();
    }

    @Override
    public Cooking getCookingById(String id) {
        return persistencePort.findById(id)
                .orElseThrow(() -> new RuntimeException("Cooking not found with id: " + id));
    }

    @Override
    public Cooking getCookingByOrderNumber(String orderNumber) {
        return persistencePort.findByOrderNumber(orderNumber)
                .orElseThrow(() -> new RuntimeException("Cooking not found with order number: " + orderNumber));
    }

    @Override
    public String startPreparation(String id) {
        Cooking cooking = getCookingById(id);
        Cooking updatedCooking = Cooking.builder()
                .id(cooking.getId())
                .orderNumber(cooking.getOrderNumber())
                .statusOrder("PREPARING")
                .createdAt(cooking.getCreatedAt())
                .updatedAt(LocalDateTime.now())
                .items(cooking.getItems())
                .preparationTime(cooking.getPreparationTime())
                .startedAt(LocalDateTime.now())
                .finishedAt(cooking.getFinishedAt())
                .build();
        
        persistencePort.save(updatedCooking);
        return "Order preparation started";
    }

    @Override
    public String finishPreparation(String id) {
        Cooking cooking = getCookingById(id);
        Cooking updatedCooking = Cooking.builder()
                .id(cooking.getId())
                .orderNumber(cooking.getOrderNumber())
                .statusOrder("READY")
                .createdAt(cooking.getCreatedAt())
                .updatedAt(LocalDateTime.now())
                .items(cooking.getItems())
                .preparationTime(cooking.getPreparationTime())
                .startedAt(cooking.getStartedAt())
                .finishedAt(LocalDateTime.now())
                .build();
        
        persistencePort.save(updatedCooking);
        return "Order is ready for pickup";
    }

    @Override
    public List<Cooking> findByStatusOrder(String statusOrder) {
        return persistencePort.findByStatusOrder(statusOrder);
    }

    @Override
    public void updateCooking(Cooking cooking) {
        persistencePort.save(cooking);
    }
}