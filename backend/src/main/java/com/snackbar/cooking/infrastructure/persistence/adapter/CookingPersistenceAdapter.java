package com.snackbar.cooking.infrastructure.persistence.adapter;

import com.snackbar.cooking.application.port.output.CookingPersistencePort;
import com.snackbar.cooking.domain.model.Cooking;
import com.snackbar.cooking.infrastructure.persistence.entity.CookingEntity;
import com.snackbar.cooking.infrastructure.persistence.mapper.CookingPersistenceMapper;
import com.snackbar.cooking.infrastructure.persistence.repository.CookingMongoRepository;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CookingPersistenceAdapter implements CookingPersistencePort {

    private final CookingMongoRepository repository;
    private final CookingPersistenceMapper mapper;

    public CookingPersistenceAdapter(CookingMongoRepository repository, CookingPersistenceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<Cooking> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Cooking> findById(String id) {
        return repository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<Cooking> findByOrderNumber(String orderNumber) {
        return repository.findByOrderNumber(orderNumber)
                .map(mapper::toDomain);
    }

    @Override
    public List<Cooking> findByStatusOrder(String statusOrder) {
        return repository.findByStatusOrder(statusOrder)
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void save(Cooking cooking) {
        CookingEntity entity = mapper.toEntity(cooking);
        repository.save(entity);
    }
}