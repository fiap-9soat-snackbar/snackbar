package com.snackbar.cooking.application;

import java.util.List;
import com.snackbar.cooking.domain.Cooking;

public interface CookingService {

    public List<Cooking> obtainAll();
    
    public Cooking getById(String id);

    public String startPreparation(String id);
    
    public String finishPreparation(String id);
}
