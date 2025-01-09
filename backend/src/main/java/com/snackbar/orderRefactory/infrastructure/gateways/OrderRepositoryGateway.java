package com.snackbar.orderRefactory.infrastructure.gateways;

import com.snackbar.orderRefactory.application.gateways.OrderGateway;
import com.snackbar.orderRefactory.domain.entity.Order;
import com.snackbar.orderRefactory.infrastructure.persistence.OrderEntity;
import com.snackbar.orderRefactory.infrastructure.persistence.OrderRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OrderRepositoryGateway implements OrderGateway {

    private final OrderRepository orderRepository;
    private final OrderEntityMapper orderEntityMapper;

    public OrderRepositoryGateway(OrderRepository orderRepository, OrderEntityMapper orderEntityMapper) {
        this.orderRepository = orderRepository;
        this.orderEntityMapper = orderEntityMapper;
    }

    @Override
    public Order createOrder(Order orderDomainObj) {
        OrderEntity orderEntity = orderEntityMapper.toEntity(orderDomainObj);
        OrderEntity savedObj = orderRepository.save(orderEntity);
        return orderEntityMapper.toDomainObj(savedObj);
    }

    @Override
    public Order updateOrder(Order orderDomainObj) {
        OrderEntity orderEntity = orderEntityMapper.toEntity(orderDomainObj);
        OrderEntity updatedObj = orderRepository.save(orderEntity);
        return orderEntityMapper.toDomainObj(updatedObj);
    }

    @Override
    public List<Order> listOrders() {
        List<OrderEntity> orderEntities = orderRepository.findAll();
        return orderEntities.stream()
                .map(orderEntityMapper::toDomainObj)
                .collect(Collectors.toList());
    }

    @Override
    public Order searchOrderId(String orderId) {
        OrderEntity orderEntity = orderRepository.findById(orderId).orElse(null);
        return orderEntity != null ? orderEntityMapper.toDomainObj(orderEntity) : null;
    }

    @Override
    public void updateStatusOrder(String orderId) {
        OrderEntity orderEntity = orderRepository.findById(orderId).orElse(null);
        if (orderEntity != null) {
            // Update the status of the order here
            orderRepository.save(orderEntity);
        }
    }

    @Override
    public Order getOrderByOrderNumber(String orderNumber) {
        Optional<OrderEntity> orderEntity = orderRepository.findByOrderNumber(orderNumber);
        return orderEntity != null ? orderEntityMapper.toDomainObj(orderEntity.orElse(null)) : null;
    }

    @Override
    public List<Order> getSortedOrders() {
        List<OrderEntity> orderEntities = orderRepository.findAllByOrderByOrderNumberAsc();
        return orderEntities.stream()
                .map(orderEntityMapper::toDomainObj)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Order> findOrderById(String orderId) {
        return orderRepository.findById(orderId)
                .map(orderEntityMapper::toDomainObj);
    }

    @Override
    public Optional<String> findUserByCpf(String cpf) {
//        return orderRepository.findUserByCpf(cpf);
        return " ".describeConstable();
    }

    @Override
    public String findTopByOrderByOrderNumberDesc() {
        return orderRepository.findTopByOrderByOrderNumberDesc();
    }
}
