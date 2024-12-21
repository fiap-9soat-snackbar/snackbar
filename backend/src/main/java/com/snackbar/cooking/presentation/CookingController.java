package com.snackbar.cooking.presentation;

import org.springframework.web.bind.annotation.*;

import com.snackbar.cooking.application.CookingService;
import com.snackbar.cooking.entity.Cooking;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api")
public class CookingController {

    @Autowired
    private CookingService cookingService;

    @GetMapping("/cooking")
    public ResponseEntity<List<Cooking>> getAllCookings() {
        List<Cooking> cookings = cookingService.findByStatusOrder("PREPARACAO");

        if (cookings.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(cookings);
    }

    @GetMapping("/cooking/get-order-by-id/{id}")
    public ResponseEntity<Cooking> getCookingById(@PathVariable String id) {
        Cooking cooking = cookingService.getById(id);
        cooking.updateRemainingWaitingTime();
        return ResponseEntity.ok(cooking);
    }

    @GetMapping("/cooking/get-order-by-number/{order_number}")
    public ResponseEntity<Cooking> getCookingByNumber(@PathVariable String order_number) {
        Cooking cooking = cookingService.getByOrderNumber(order_number);
        cooking.updateRemainingWaitingTime();
        return ResponseEntity.ok(cooking);
    }

    @PutMapping("/cooking/receive-order/{id}")
    public ResponseEntity<String> receiveOrder(@PathVariable String id) {
        String response = cookingService.receiveOrder(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/cooking/start-preparation/{id}")
    public ResponseEntity<String> startPreparation(@PathVariable String id) {
        String response = cookingService.startPreparation(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/cooking/finish-preparation/{id}")
    public ResponseEntity<String> finishPreparation(@PathVariable String id) {
        String response = cookingService.finishPreparation(id);
        return ResponseEntity.ok(response);
    }
}
