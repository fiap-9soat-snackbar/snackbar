package com.snackbar.cooking.infrastructure.gateways;

import com.snackbar.cooking.application.gateways.CookingGateway;
import com.snackbar.cooking.domain.entity.Cooking;
import com.snackbar.cooking.infrastructure.persistence.CookingRepository;
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