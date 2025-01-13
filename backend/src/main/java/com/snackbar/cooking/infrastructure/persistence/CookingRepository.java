package com.snackbar.cooking.infrastructure.persistence;

import com.snackbar.cooking.domain.entity.Cooking;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CookingRepository extends MongoRepository<Cooking, String> {
    List<Cooking> findByStatusOrderIn(List<String> statuses);
    Cooking findByOrderNumber(String orderNumber);
}