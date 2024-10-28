package com.snackbar.checkout.adapter.out;

import com.snackbar.checkout.domain.model.Checkout;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CheckoutRepository extends MongoRepository<Checkout, String> {

    Optional<Checkout> findByOrderId(String orderId);
}
