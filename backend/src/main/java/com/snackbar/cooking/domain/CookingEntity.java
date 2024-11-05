package com.snackbar.cooking.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CookingEntity {

    @Id
    private String id;

    private String customerName;

    private String customerId;

    private String orderNumber;

    private LocalDateTime orderDateTime;

    private List<Item> items;

    private String statusOrder;

    private int waitingTime;

    private long remainingWaitingTime;

    private String paymentMethod;

    private int totalPrice;

    @Data
    public static class Item {
        private String name;
        private int price;
        private int quantity;
        private int cookingTime;
        private String customization;
    }
}