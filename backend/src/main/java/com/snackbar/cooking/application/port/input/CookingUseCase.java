package com.snackbar.cooking.application.port.input;

import com.snackbar.cooking.domain.model.Cooking;
import java.util.List;

public interface CookingUseCase {
    List<Cooking> getAllCookings();
    Cooking getCookingById(String id);
    Cooking getCookingByOrderNumber(String orderNumber);
    String startPreparation(String id);
    String finishPreparation(String id);
    List<Cooking> findByStatusOrder(String statusOrder);
    void updateCooking(Cooking cooking);
}