package com.snackbar.payment.infrastructure.persistence;

/* This should be equivalent to the PaymentRepositoryImpl, therefore actually having 
dependencies to a specific framework or library - in this case both Spring and MongoDB.*/

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PaymentRepository extends MongoRepository<PaymentEntity, String> {
    Optional<PaymentEntity> findByOrderId(String orderId);
}