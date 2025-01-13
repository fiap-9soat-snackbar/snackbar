package com.snackbar.cooking.application.gateways;

import com.snackbar.cooking.domain.entity.Cooking;
import java.util.List;
import java.util.Optional;

public interface CookingGateway {
    List<Cooking> findByStatusOrderIn(List<String> statuses);
    Optional<Cooking> findById(String id);
    Cooking findByOrderNumber(String orderNumber);
    Cooking save(Cooking cooking);
}