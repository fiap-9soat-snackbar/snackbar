package com.snackbar.cooking.infrastructure.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface CookingRepository extends MongoRepository<CookingEntity, String> {
    Optional<CookingEntity> findByOrderId(String orderId);
}