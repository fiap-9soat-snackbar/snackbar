package com.snackbar.cooking.web;

import org.springframework.web.bind.annotation.*;

import com.snackbar.cooking.domain.Cooking;
import com.snackbar.cooking.application.CookingService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api")
public class CookingController {

    @Autowired
    private CookingService cookingService;

    @GetMapping("/cooking/nome_cliente/{id}")
    public ResponseEntity<String> getClientNameById(@PathVariable String id) {
        Cooking cooking = this.cookingService.getById(id);
        String ClientName = cooking.getClientName();
        return ResponseEntity.ok(ClientName);
    }

    @GetMapping("/cooking")
    public ResponseEntity<List<Cooking>> getAllCookings() {
        List<Cooking> cookings = this.cookingService.obtainAll();
        return ResponseEntity.ok(cookings);
    }

    @GetMapping("/cooking/{id}")
    public ResponseEntity<Cooking> getCookingById(@PathVariable String id) {
        Cooking cooking = this.cookingService.getById(id);
        return ResponseEntity.ok(cooking);
    }

    @PutMapping("/cooking/{id}/start-preparation")
    public ResponseEntity<String> startPreparation(@PathVariable String id) {
        String result = cookingService.startPreparation(id);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/cooking/{id}/finish-preparation")
    public ResponseEntity<String> finishPreparation(@PathVariable String id) {
        String result = cookingService.finishPreparation(id);
        return ResponseEntity.ok(result);
    }
}
