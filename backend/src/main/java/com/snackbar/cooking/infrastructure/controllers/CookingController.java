package com.snackbar.cooking.infrastructure.controllers;

import com.snackbar.cooking.application.usecases.*;
import com.snackbar.cooking.domain.entity.Cooking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/cooking")
public class CookingController {

    private final ReceiveOrderUseCase receiveOrderUseCase;
    private final StartPreparationUseCase startPreparationUseCase;
    private final FinishPreparationUseCase finishPreparationUseCase;

    @Autowired
    public CookingController(
            ReceiveOrderUseCase receiveOrderUseCase,
            StartPreparationUseCase startPreparationUseCase,
            FinishPreparationUseCase finishPreparationUseCase) {
        this.receiveOrderUseCase = receiveOrderUseCase;
        this.startPreparationUseCase = startPreparationUseCase;
        this.finishPreparationUseCase = finishPreparationUseCase;
    }

    @GetMapping
    public ResponseEntity<List<Cooking>> getAllCookings() {
        // Simplified logic for the controller
        return ResponseEntity.ok(Arrays.asList());
    }

    @PostMapping("/receive/{id}")
    public ResponseEntity<String> receiveOrder(@PathVariable String id) {
        return ResponseEntity.ok(receiveOrderUseCase.execute(id));
    }

    @PostMapping("/start/{id}")
    public ResponseEntity<String> startPreparation(@PathVariable String id) {
        return ResponseEntity.ok(startPreparationUseCase.execute(id));
    }

    @PostMapping("/finish/{id}")
    public ResponseEntity<String> finishPreparation(@PathVariable String id) {
        return ResponseEntity.ok(finishPreparationUseCase.execute(id));
    }
}