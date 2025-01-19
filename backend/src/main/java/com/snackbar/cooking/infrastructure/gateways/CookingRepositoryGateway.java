package com.snackbar.cooking.infrastructure.gateways;

import com.snackbar.cooking.application.gateways.CookingGateway;
import com.snackbar.cooking.domain.entity.Cooking;
import com.snackbar.cooking.infrastructure.persistence.CookingEntity;
import com.snackbar.cooking.infrastructure.persistence.CookingRepository;
import com.snackbar.order.domain.model.StatusOrder;

import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CookingRepositoryGateway implements CookingGateway {
    private final CookingRepository cookingRepository;
    private final CookingEntityMapper cookingMapper;

    public CookingRepositoryGateway(CookingRepository cookingRepository, CookingEntityMapper cookingMapper) {
        this.cookingRepository = cookingRepository;
        this.cookingMapper = cookingMapper;
    }

    @Override
    public Cooking createCooking(Cooking cooking) {
        var entity = cookingMapper.toEntity(cooking);
        var savedEntity = cookingRepository.save(entity);
        return cookingMapper.toDomainObj(savedEntity);
    }

    @Override
    public Cooking updateCookingStatus(String orderId, StatusOrder status) {
        System.out.println("orderId: " + orderId);
        System.out.println("status: " + status);
        System.out.println("Obtendo o cooking:");
        CookingEntity entity = cookingRepository.findByOrderId(orderId);
        System.out.println("entity: " + entity);
        if (entity == null) {
            throw new RuntimeException("Cooking not found for orderId: " + orderId);
        }
        
        entity.setStatus(status);
        var updatedEntity = cookingRepository.save(entity);
        return cookingMapper.toDomainObj(updatedEntity);
    }

    @Override
    public Cooking findByOrderId(String orderId) {
        CookingEntity entity = cookingRepository.findByOrderId(orderId);
        return cookingMapper.toDomainObj(entity);
    }

    // @Override
    // public List<Cooking> listCookings() {
    //     return repository.findAll().stream()
    //             .map(mapper::toDomainObj)
    //             .collect(Collectors.toList());
    // }

    // @Override
    // public Cooking getCookingById(String id) {
    //     return repository.findById(id)
    //             .map(mapper::toDomainObj)
    //             .orElseThrow(() -> new RuntimeException("Cooking not found"));
    // }

    // @Override
    // public Cooking getCookingByOrderId(String orderId) {
    //     return repository.findByOrderId(orderId)
    //             .map(mapper::toDomainObj)
    //             .orElseThrow(() -> new RuntimeException("Cooking not found"));
    // }

    // @Override
    // public Cooking updateCookingStatus(String id, Enum status) {
    //     var entity = repository.findById(id)
    //             .orElseThrow(() -> new RuntimeException("Cooking not found"));
    //     entity.setStatus(status);
    //     var updatedEntity = repository.save(entity);
    //     return mapper.toDomainObj(updatedEntity);
    // }
}