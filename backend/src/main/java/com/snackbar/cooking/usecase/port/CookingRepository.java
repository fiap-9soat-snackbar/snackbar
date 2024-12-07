package com.snackbar.cooking.usecase.port;

import com.snackbar.cooking.domain.entity.CookingEntity;
import java.util.List;

public interface CookingRepository {
    List<CookingEntity> findAll();
    CookingEntity findById(String id);
    CookingEntity findByOrderNumber(String orderNumber);
    List<CookingEntity> findByStatusOrder(String statusOrder);
    void save(CookingEntity cooking);
}