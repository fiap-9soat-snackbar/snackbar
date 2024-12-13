package com.snackbar.cooking.infrastructure.persistence.repository;

import com.snackbar.cooking.infrastructure.persistence.entity.CookingEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import java.util.Optional;

public interface CookingMongoRepository extends MongoRepository<CookingEntity, String> {
    Optional<CookingEntity> findByOrderNumber(String orderNumber);
    List<CookingEntity> findByStatusOrder(String statusOrder);
}