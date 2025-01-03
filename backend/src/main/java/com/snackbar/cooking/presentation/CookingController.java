package com.snackbar.cooking.presentation;

import org.springframework.web.bind.annotation.*;

import com.snackbar.cooking.application.usecase.*;
import com.snackbar.cooking.entity.Cooking;
import com.snackbar.cooking.gateway.CookingRepository;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api")
public class CookingController {

    private final CookingRepository cookingRepository;
    private final ReceiveOrderUseCase receiveOrderUseCase;
    private final StartPreparationUseCase startPreparationUseCase;
    private final FinishPreparationUseCase finishPreparationUseCase;

    @Autowired
    public CookingController(
            CookingRepository cookingRepository,
            ReceiveOrderUseCase receiveOrderUseCase,
            StartPreparationUseCase startPreparationUseCase,
            FinishPreparationUseCase finishPreparationUseCase) {
        this.cookingRepository = cookingRepository;
        this.receiveOrderUseCase = receiveOrderUseCase;
        this.startPreparationUseCase = startPreparationUseCase;
        this.finishPreparationUseCase = finishPreparationUseCase;
    }

    @GetMapping("/cooking")
    public ResponseEntity<List<Cooking>> getAllCookings() {
        List<String> statuses = Arrays.asList("PREPARACAO", "RECEBIDO", "PRONTO");
        List<Cooking> cookings = cookingRepository.findByStatusOrderIn(statuses);

        if (cookings.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(cookings);
    }

    @GetMapping("/cooking/get-order-by-id/{id}")
    public ResponseEntity<Cooking> getCookingById(@PathVariable String id) {
        Cooking cooking = cookingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cooking not found"));
        cooking.updateRemainingWaitingTime();
        return ResponseEntity.ok(cooking);
    }

    @GetMapping("/cooking/get-order-by-number/{order_number}")
    public ResponseEntity<Cooking> getCookingByNumber(@PathVariable String order_number) {
        Cooking cooking = cookingRepository.findByOrderNumber(order_number);
        cooking.updateRemainingWaitingTime();
        return ResponseEntity.ok(cooking);
    }

    @PostMapping("/cooking/receive-order/{id}")
    public ResponseEntity<String> receiveOrder(@PathVariable String id) {
        String response = receiveOrderUseCase.execute(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/cooking/start-preparation/{id}")
    public ResponseEntity<String> startPreparation(@PathVariable String id) {
        String response = startPreparationUseCase.execute(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/cooking/finish-preparation/{id}")
    public ResponseEntity<String> finishPreparation(@PathVariable String id) {
        String response = finishPreparationUseCase.execute(id);
        return ResponseEntity.ok(response);
    }
}
