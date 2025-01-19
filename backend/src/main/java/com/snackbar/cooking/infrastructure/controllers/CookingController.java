package com.snackbar.cooking.infrastructure.controllers;

import com.snackbar.cooking.application.usecases.*;
import com.snackbar.cooking.domain.entity.Cooking;
import com.snackbar.cooking.infrastructure.persistence.CookingEntity;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cooking")
public class CookingController {
    private final CreateCookingUseCase createCookingUseCase;
    private final StartPreparationUseCase startPreparationUseCase;
    private final FinishPreparationUseCase finishPreparationUseCase;
    private final CookingDTOMapper cookingDTOMapper;

    public CookingController(CreateCookingUseCase createCookingUseCase, StartPreparationUseCase startPreparationUseCase, FinishPreparationUseCase finishPreparationUseCase, CookingDTOMapper cookingDTOMapper) {
        this.createCookingUseCase = createCookingUseCase;
        this.startPreparationUseCase = startPreparationUseCase;
        this.finishPreparationUseCase = finishPreparationUseCase;
        this.cookingDTOMapper = cookingDTOMapper;
    }

    @PostMapping("/receive-order/{id}")
    public ResponseEntity<CreateCookingResponse> receiveOrder(@PathVariable String id) {
        Cooking cooking = cookingDTOMapper.createRequestToDomain(id);
        Cooking createdCooking = createCookingUseCase.createCooking(cooking);
        CreateCookingResponse response = cookingDTOMapper.createToResponse(createdCooking);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/start-preparation/{id}")
    public ResponseEntity<CreateCookingResponse> startPreparation(@PathVariable String id) {
        Cooking cooking = cookingDTOMapper.createRequestToDomain(id);
        Cooking result = startPreparationUseCase.updateCooking(cooking);
        CreateCookingResponse response = cookingDTOMapper.createToResponse(result);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/finish-preparation/{id}")
    public ResponseEntity<CreateCookingResponse> finishPreparation(@PathVariable String id) {
        Cooking cooking = cookingDTOMapper.createRequestToDomain(id);
        Cooking result = finishPreparationUseCase.updateCooking(cooking);
        CreateCookingResponse response = cookingDTOMapper.createToResponse(result);
        return ResponseEntity.ok(response);
    }
}