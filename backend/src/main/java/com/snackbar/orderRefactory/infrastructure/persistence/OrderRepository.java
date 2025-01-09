package com.snackbar.orderRefactory.infrastructure.persistence;

import com.snackbar.orderRefactory.domain.entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends MongoRepository<OrderEntity, String> {
    String findTopByOrderByOrderNumberDesc();
    List<OrderEntity> findAllByOrderByOrderNumberAsc();
    Optional<OrderEntity> findByOrderNumber(String orderNumber);
}
