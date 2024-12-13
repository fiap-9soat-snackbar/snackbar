package com.snackbar.cooking.application.port.output;

import com.snackbar.cooking.domain.model.Cooking;
import java.util.List;
import java.util.Optional;

public interface CookingPersistencePort {
    List<Cooking> findAll();
    Optional<Cooking> findById(String id);
    Optional<Cooking> findByOrderNumber(String orderNumber);
    List<Cooking> findByStatusOrder(String statusOrder);
    void save(Cooking cooking);
}