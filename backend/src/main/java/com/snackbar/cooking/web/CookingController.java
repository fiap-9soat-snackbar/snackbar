package com.snackbar.cooking.web;

import com.snackbar.cooking.application.port.input.CookingUseCase;
import com.snackbar.cooking.domain.model.Cooking;
import com.snackbar.cooking.web.dto.CookingDTO;
import com.snackbar.cooking.web.mapper.CookingWebMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDateTime;
import java.time.Duration;
@RestController
@RequestMapping("/api")
public class CookingController {

    private final CookingUseCase cookingUseCase;
    private final CookingWebMapper mapper;

    public CookingController(CookingUseCase cookingUseCase, CookingWebMapper mapper) {
        this.cookingUseCase = cookingUseCase;
        this.mapper = mapper;
    }

    @GetMapping("/cooking")
    public ResponseEntity<List<CookingDTO>> getAllCookings() {
        var cookings = cookingUseCase.findByStatusOrder("PREPARACAO")
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
        
        if (cookings.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        
        return ResponseEntity.ok(cookings);
    }

    @GetMapping("/cooking/get-order-by-id/{id}")
    public ResponseEntity<CookingDTO> getCookingById(@PathVariable String id) {
        var cooking = cookingUseCase.getCookingById(id);
        return ResponseEntity.ok(mapper.toDTO(cooking));
    }

    @GetMapping("/cooking/get-order-by-number/{order_number}")
    public ResponseEntity<CookingDTO> getCookingByNumber(@PathVariable String order_number) {
        var cooking = cookingUseCase.getCookingByOrderNumber(order_number);
        return ResponseEntity.ok(mapper.toDTO(cooking));
    }

    @PutMapping("/cooking/start-preparation/{id}")
    public ResponseEntity<String> startPreparation(@PathVariable String id) {
        try {
            String result = cookingUseCase.startPreparation(id);
            return ResponseEntity.ok(result);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    @PutMapping("/cooking/finish-preparation/{id}")
    public ResponseEntity<String> finishPreparation(@PathVariable String id) {
        try {
            String result = cookingUseCase.finishPreparation(id);
            return ResponseEntity.ok(result);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    private void defineRemainingAwatingTime (Cooking cooking) {

        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime dateTime = cooking.getOrderDateTime();
        int minutesToAdd = cooking.getWaitingTime();
        LocalDateTime deliverTime = dateTime.plusMinutes(minutesToAdd);
        long remainingWaitingTime = Duration.between(currentDateTime, deliverTime).toMinutes();
        remainingWaitingTime = remainingWaitingTime <= 0 ? 0 : remainingWaitingTime;
        cooking.setRemainingWaitingTime(remainingWaitingTime);

        return;
    }
}
