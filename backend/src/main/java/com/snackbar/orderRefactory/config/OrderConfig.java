package com.snackbar.orderRefactory.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.snackbar.orderRefactory.application.usecases.OrderUseCase;
import com.snackbar.orderRefactory.infrastructure.controllers.OrderDTOMapper;
import com.snackbar.orderRefactory.infrastructure.gateways.OrderEntityMapper;
import com.snackbar.orderRefactory.infrastructure.gateways.OrderRepositoryGateway;
import com.snackbar.orderRefactory.infrastructure.persistence.OrderRepository;

@Configuration
public class OrderConfig {
    @Bean
    OrderUseCase orderUseCase(OrderRepositoryGateway orderRepositoryGateway) {
        return new OrderUseCase(orderRepositoryGateway);
    }

    @Bean
    OrderRepositoryGateway orderRepositoryGateway(OrderRepository orderRepository, OrderEntityMapper orderEntityMapper) {
        return new OrderRepositoryGateway(orderRepository, orderEntityMapper);
    }

    @Bean
    OrderEntityMapper orderEntityMapper() {
        return new OrderEntityMapper();
    }

    @Bean
    OrderDTOMapper orderDTOMapper() {
        return new OrderDTOMapper();
    }
}
