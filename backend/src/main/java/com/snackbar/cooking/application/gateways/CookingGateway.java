package com.snackbar.cooking.application.gateways;

import com.snackbar.cooking.domain.entity.Cooking;
import java.util.List;

public interface CookingGateway {
    Cooking createCooking(Cooking cooking);
    // List<Cooking> listCookings();
    // Cooking getCookingById(String id);
    // Cooking getCookingByOrderId(String orderId);
    // Cooking updateCookingStatus(String id, String status);
}