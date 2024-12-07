package com.snackbar.cooking.usecase.port;

import com.snackbar.cooking.domain.entity.CookingEntity;
import java.util.List;

public interface CookingUseCase {
    List<CookingEntity> getAllCookings();
    CookingEntity getById(String id);
    CookingEntity getByOrderNumber(String orderNumber);
    String startPreparation(String id);
    String finishPreparation(String id);
    List<CookingEntity> findByStatusOrder(String statusOrder);
    void updateOrder(CookingEntity cooking);
}