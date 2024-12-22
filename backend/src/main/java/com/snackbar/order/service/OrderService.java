package com.snackbar.order.service;

import com.snackbar.order.domain.model.Order;
import com.snackbar.order.domain.model.StatusOrder;
import com.snackbar.order.domain.model.Item;
import com.snackbar.order.adapter.out.OrderRepository;
import com.snackbar.product.usecase.GetProductByNameUseCase;
import com.snackbar.product.dto.ProductDTO;
import org.springframework.stereotype.Service;

import com.snackbar.pickup.entity.StatusPickup;
import com.snackbar.pickup.gateway.PickupRepository;
import com.snackbar.iam.infrastructure.UserRepository;
import com.snackbar.checkout.gateway.CheckoutRepository;
import com.snackbar.iam.domain.UserEntity;
import java.util.List;
import java.util.stream.Collectors;
import java.math.BigDecimal;
import java.time.Instant;

@Service
public class OrderService { // Service for managing orders

    private final OrderRepository orderRepository;
    private final GetProductByNameUseCase getProductByNameUseCase;
    private final CheckoutRepository checkoutRepository;
    private final PickupRepository pickupRepository;
    private final UserRepository userRepository;

    public OrderService(OrderRepository orderRepository, GetProductByNameUseCase getProductByNameUseCase, CheckoutRepository checkoutRepository, PickupRepository pickupRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.getProductByNameUseCase = getProductByNameUseCase;
        this.checkoutRepository = checkoutRepository;
        this.pickupRepository = pickupRepository;
        this.userRepository = userRepository;
    }

    // Create new order
    public Order createOrder(Order order) {
        if (order.getCpf() == null) {
            throw new IllegalArgumentException("CPF is mandatory for creating an order");
        }
        
        // Validate if the user exists and get the user's name
        String userName = userRepository.findByCpf(order.getCpf())
            .map(UserEntity::getName)
            .orElseThrow(() -> new IllegalArgumentException("User with provided CPF does not exist"));
        
        order.setName(userName);

        String lastOrderNumber = orderRepository.findTopByOrderByOrderNumberDesc()
                .map(Order::getOrderNumber)
                .orElse(null);
        order.setOrderNumber(Order.generateOrderNumber(lastOrderNumber));
        order.setStatusOrder(StatusOrder.NOVO);
        order.setOrderDateTime(Instant.now()); // Set orderDateTime to current time in ISO format
        
        List<Item> updatedItems = order.getItems().stream().map(item -> {
            ProductDTO product = getProductByNameUseCase.getProductByName(item.getName());
            if (product != null) {
                item.setName(product.getName());  // Explicitly set the product name
                item.setPrice(product.getPrice());
                item.setCookingTime(product.getCookingTime());
            } else {
                throw new IllegalArgumentException("Product not found: " + item.getName());
            }
            return item;
        }).collect(Collectors.toList());
        
        order.setItems(updatedItems);
        
        // Calculate and set the total price
        BigDecimal totalPrice = updatedItems.stream()
            .map(item -> item.getPrice() != null ? item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())) : BigDecimal.ZERO)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        order.setTotalPrice(totalPrice);
        
        // No need to set waiting time as it's now calculated dynamically in Order class
        
        return orderRepository.save(order);
    }

    // Update an existing order
    public Order updateOrder(Order order) {
        // Fetch the existing order
        Order existingOrder = orderRepository.findById(order.getId())
            .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        // Update items
        List<Item> updatedItems = order.getItems().stream().map(item -> {
            ProductDTO product = getProductByNameUseCase.getProductByName(item.getName());
            if (product != null) {
                item.setName(product.getName());
                item.setPrice(product.getPrice());
                item.setCookingTime(product.getCookingTime());
            } else {
                throw new IllegalArgumentException("Product not found: " + item.getName());
            }
            return item;
        }).collect(Collectors.toList());
        
        existingOrder.setItems(updatedItems);

        // Calculate and set the total price
        BigDecimal totalPrice = updatedItems.stream()
            .map(item -> item.getPrice() != null ? item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())) : BigDecimal.ZERO)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        existingOrder.setTotalPrice(totalPrice);

        // No need to set waiting time as it's now calculated dynamically in Order class

        return orderRepository.save(existingOrder);
    }

    // List all orders
    public List<Order> listOrders() {
        return orderRepository.findAll();
    }

    // Search an order with status
    public Order searchOrderId(String orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not Found"));
        return orderRepository.findById(orderId).orElse(order);
    }

    // Update the Order status based on Checkout and Pickup statuses
    public void updateStatusOrder(String orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado"));

        // Check if the order has been paid
        boolean isPaid = checkoutRepository.findByOrderId(orderId)
                .map(checkout -> checkout.isPaid())
                .orElse(false);

        // Check if the order is ready for pickup or has been picked up
        boolean isReady = pickupRepository.findByOrderId(orderId)
        .map(pickup -> pickup.getStatusPickup() == StatusPickup.PRONTO)
        .orElse(false);

        // Check if the order has been picked up
        boolean isDone = pickupRepository.findByOrderId(orderId)
                .map(pickup -> pickup.getStatusPickup() == StatusPickup.FINALIZADO)
                .orElse(false);

        // Update StatusOrder based on payment and pickup status
        if (isPaid && isDone) {
            order.setStatusOrder(StatusOrder.FINALIZADO);
        } else if (isReady) {
            order.setStatusOrder(StatusOrder.PRONTO);
        } else if (isPaid) {
            order.setStatusOrder(StatusOrder.PAGO);
            order.setPaymentMethod("Mercado Pago");
        } else {
            order.setStatusOrder(StatusOrder.NOVO);
        }

        orderRepository.save(order);
    }

    // Search order by orderNumber
    public Order getOrderByOrderNumber(String orderNumber) {
        return orderRepository.findByOrderNumber(orderNumber)
                .orElseThrow(() -> new IllegalArgumentException("Order not found with orderNumber: " + orderNumber));
    }

    // Get all orders sorted by status and orderDateTime
    public List<Order> getSortedOrders() {
        return orderRepository.findAll().stream()
                .filter(order -> order.getStatusOrder() == StatusOrder.PRONTO ||
                                 order.getStatusOrder() == StatusOrder.PREPARACAO ||
                                 order.getStatusOrder() == StatusOrder.RECEBIDO)
                .sorted((o1, o2) -> {
                    int statusComparison = compareStatus(o1.getStatusOrder(), o2.getStatusOrder());
                    if (statusComparison != 0) {
                        return statusComparison;
                    }
                    return o1.getOrderDateTime().compareTo(o2.getOrderDateTime());
                })
                .collect(Collectors.toList());
    }

    private int compareStatus(StatusOrder status1, StatusOrder status2) {
        if (status1 == status2) return 0;
        if (status1 == StatusOrder.PRONTO) return -1;
        if (status2 == StatusOrder.PRONTO) return 1;
        if (status1 == StatusOrder.PREPARACAO) return -1;
        if (status2 == StatusOrder.PREPARACAO) return 1;
        return 0;
    }
}
