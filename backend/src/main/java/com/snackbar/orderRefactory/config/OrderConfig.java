package com.snackbar.orderRefactory.config;

import com.snackbar.checkout.usecase.CheckoutOrderUseCaseImpl;
import com.snackbar.orderRefactory.infrastructure.gateways.ProductDTOToOrderItemMapper;
import com.snackbar.orderRefactory.infrastructure.gateways.ProductServiceGateway;
import com.snackbar.pickup.usecase.IsReadyPickupUseCaseImpl;
import com.snackbar.product.usecase.impl.GetProductByNameUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.snackbar.orderRefactory.application.usecases.OrderUseCase;
import com.snackbar.orderRefactory.infrastructure.controllers.OrderDTOMapper;
import com.snackbar.orderRefactory.infrastructure.gateways.OrderEntityMapper;
import com.snackbar.orderRefactory.infrastructure.gateways.OrderRepositoryGateway;
import com.snackbar.orderRefactory.infrastructure.persistence.OrderRefactoryRepository;

@Configuration
public class OrderConfig {
    @Bean
    OrderUseCase orderUseCase(
            OrderRepositoryGateway orderRepositoryGateway,
            ProductServiceGateway productServiceGateway,
            CheckoutOrderUseCaseImpl checkoutOrderUseCaseImpl,
            IsReadyPickupUseCaseImpl isReadyPickupUseCaseImpl
    ) {
        return new OrderUseCase(orderRepositoryGateway, productServiceGateway, checkoutOrderUseCaseImpl, isReadyPickupUseCaseImpl);
    }

    @Bean
    OrderRepositoryGateway orderRepositoryGateway(OrderRefactoryRepository orderRefactoryRepository, OrderEntityMapper orderEntityMapper) {
        return new OrderRepositoryGateway(orderRefactoryRepository, orderEntityMapper);
    }

    @Bean
    ProductDTOToOrderItemMapper productDTOToOrderItemMapper() {
        return new ProductDTOToOrderItemMapper();
    }

    @Bean
    ProductServiceGateway productServiceGateway(GetProductByNameUseCaseImpl productUseCase, ProductDTOToOrderItemMapper productDTOToOrderItemMapper) {
        return new ProductServiceGateway(productUseCase, productDTOToOrderItemMapper);
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
