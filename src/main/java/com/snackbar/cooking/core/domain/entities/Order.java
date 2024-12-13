package com.snackbar.cooking.core.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "orders")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private String id;
    private String orderNumber;
    private String customerName;
    private String statusOrder;
    private Double totalAmount;
    private List<Item> items;
    private Double preparationTime;
    private Double remainingTime;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Item {
        private String productId;
        private String name;
        private Integer quantity;
        private Double price;
        private Double totalPrice;
    }
}