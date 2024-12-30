package com.snackbar.basket.gateway;

import com.snackbar.basket.entity.Basket;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BasketRepository extends MongoRepository<Basket, String> {
    Optional<Basket> findByOrderId(String orderId);
}
