package com.snackbar.cooking.adapter.web;

import com.snackbar.cooking.usecase.port.CookingUseCase;
import com.snackbar.cooking.domain.entity.CookingEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CookingController {
    private final CookingUseCase cookingUseCase;

    public CookingController(CookingUseCase cookingUseCase) {
        this.cookingUseCase = cookingUseCase;
    }

    @GetMapping("/cooking")
    public ResponseEntity<List<CookingEntity>> getAllCookings() {
        return ResponseEntity.ok(cookingUseCase.getAllCookings());
    }

    @GetMapping("/cooking/get-order-by-id/{id}")
    public ResponseEntity<CookingEntity> getCookingById(@PathVariable String id) {
        CookingEntity cooking = cookingUseCase.getById(id);
        if (cooking != null) {
            return ResponseEntity.ok(cooking);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/cooking/get-order-by-number/{order_number}")
    public ResponseEntity<CookingEntity> getCookingByNumber(@PathVariable String order_number) {
        CookingEntity cooking = cookingUseCase.getByOrderNumber(order_number);
        if (cooking != null) {
            return ResponseEntity.ok(cooking);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/cooking/start-preparation/{id}")
    public ResponseEntity<String> startPreparation(@PathVariable String id) {
        String result = cookingUseCase.startPreparation(id);
        if ("Order not found".equals(result)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @PutMapping("/cooking/finish-preparation/{id}")
    public ResponseEntity<String> finishPreparation(@PathVariable String id) {
        String result = cookingUseCase.finishPreparation(id);
        if ("Order not found".equals(result)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }
}