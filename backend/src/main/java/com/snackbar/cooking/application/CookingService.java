package com.snackbar.cooking.application;

import java.util.List;

import com.snackbar.cooking.entity.Cooking;

public interface CookingService {

    public List<Cooking> obtainAll();
    
    public Cooking getById(String id);

    public Cooking getByOrderNumber(String orderNumber);

    public String startPreparation(String id);
    
    public String finishPreparation(String id);

    public List<Cooking> findByStatusOrder(String statusOrder);

    public void updateOrder(Cooking cooking);
    

}
