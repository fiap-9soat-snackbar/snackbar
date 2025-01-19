package com.snackbar.cooking.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.snackbar.cooking.application.gateways.CookingGateway;
import com.snackbar.cooking.infrastructure.gateways.CookingEntityMapper;
import com.snackbar.cooking.infrastructure.gateways.CookingRepositoryGateway;
import com.snackbar.cooking.infrastructure.persistence.CookingRepository;
import com.snackbar.cooking.application.usecases.CreateCookingUseCase;
import com.snackbar.order.service.OrderService;
import com.snackbar.cooking.infrastructure.controllers.CookingDTOMapper;

@Configuration
public class CookingConfig {

    @Bean
    public CookingDTOMapper cookingDTOMapper() {
        return new CookingDTOMapper();
    }

    @Bean
    public CookingEntityMapper cookingEntityMapper() {
        return new CookingEntityMapper();
    }

    @Bean
    public CookingGateway cookingGateway(CookingRepository cookingRepository, CookingEntityMapper cookingMapper) {
        return new CookingRepositoryGateway(cookingRepository, cookingMapper);
    }

    @Bean
    public CreateCookingUseCase createCookingUseCase(CookingGateway cookingGateway, OrderService orderService) {
        return new CreateCookingUseCase(cookingGateway, orderService);
    }

}