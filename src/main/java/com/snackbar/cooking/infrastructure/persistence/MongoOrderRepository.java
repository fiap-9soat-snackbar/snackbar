package com.snackbar.cooking.infrastructure.persistence;

import com.snackbar.cooking.core.domain.entities.Order;
import com.snackbar.cooking.core.domain.repositories.OrderRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MongoOrderRepository extends MongoRepository<Order, String>, OrderRepository {
    @Override
    List<Order> findByStatusOrder(String statusOrder);
    
    @Override
    Order findByOrderNumber(String orderNumber);
}