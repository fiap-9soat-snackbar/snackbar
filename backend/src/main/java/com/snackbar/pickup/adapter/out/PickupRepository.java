package com.snackbar.pickup.adapter.out;

import com.snackbar.pickup.domain.model.Pickup;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PickupRepository extends MongoRepository<Pickup, String> {
   
    Optional<Pickup> findByOrderId(String orderId);
}