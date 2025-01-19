package com.snackbar.cooking.infrastructure.controllers;

import com.snackbar.cooking.application.usecases.*;
import com.snackbar.cooking.domain.entity.Cooking;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cooking")
public class CookingController {
    private final CreateCookingUseCase createCookingUseCase;
    private final CookingDTOMapper cookingDTOMapper;

    public CookingController(CreateCookingUseCase createCookingUseCase, CookingDTOMapper cookingDTOMapper) {
        this.createCookingUseCase = createCookingUseCase;
        this.cookingDTOMapper = cookingDTOMapper;
    }
    

    // @PostMapping("/receive-order/{id}")
    // public ResponseEntity<String> receiveOrder(@PathVariable String id) {
    //     try {
    //         String result = receiveOrderUseCase.execute(id);
    //         return ResponseEntity.ok(result);
    //     } catch (RuntimeException e) {
    //         return ResponseEntity.notFound().build();
    //     }
    // }

    @PostMapping("/receive-order")
    public ResponseEntity<CreateCookingResponse> createCooking(@RequestBody CreateCookingRequest request) {
        Cooking cooking = cookingDTOMapper.createRequestToDomain(request);
        Cooking createdCooking = createCookingUseCase.createCooking(cooking);
        CreateCookingResponse response = cookingDTOMapper.createToResponse(createdCooking);
        return ResponseEntity.ok(response);
    }
}