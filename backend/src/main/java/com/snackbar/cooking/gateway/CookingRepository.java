package com.snackbar.cooking.gateway;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.snackbar.cooking.entity.Cooking;

public interface CookingRepository extends MongoRepository<Cooking, String> {
    List<Cooking> findByStatusOrder(String statusOrder);
    Cooking findByOrderNumber(String orderNumber);
}
