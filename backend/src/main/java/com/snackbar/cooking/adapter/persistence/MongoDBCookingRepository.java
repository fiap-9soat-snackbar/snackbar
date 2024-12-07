package com.snackbar.cooking.adapter.persistence;

import com.snackbar.cooking.domain.entity.CookingEntity;
import com.snackbar.cooking.usecase.port.CookingRepository;
import com.snackbar.cooking.adapter.persistence.model.CookingDocument;
import com.snackbar.cooking.adapter.mapper.CookingMapper;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class MongoDBCookingRepository implements CookingRepository {
    private final SpringMongoRepository repository;
    private final CookingMapper mapper;

    public MongoDBCookingRepository(SpringMongoRepository repository, CookingMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<CookingEntity> findAll() {
        return repository.findAll().stream()
                .map(mapper::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public CookingEntity findById(String id) {
        return repository.findById(id)
                .map(mapper::toEntity)
                .orElse(null);
    }

    @Override
    public CookingEntity findByOrderNumber(String orderNumber) {
        return mapper.toEntity(repository.findByOrderNumber(orderNumber));
    }

    @Override
    public List<CookingEntity> findByStatusOrder(String statusOrder) {
        return repository.findByStatusOrder(statusOrder).stream()
                .map(mapper::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void save(CookingEntity cooking) {
        repository.save(mapper.toDocument(cooking));
    }

    interface SpringMongoRepository extends MongoRepository<CookingDocument, String> {
        List<CookingDocument> findByStatusOrder(String statusOrder);
        CookingDocument findByOrderNumber(String orderNumber);
    }
}