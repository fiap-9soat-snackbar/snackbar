package com.snackbar.payment.infrastructure.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PaymentMPRepository extends MongoRepository<PaymentMPEntity, String> {

}