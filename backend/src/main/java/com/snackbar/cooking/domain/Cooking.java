package com.snackbar.cooking.domain;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
@Document(collection = "orders")
public class Cooking {

    @Id
    private String id;

    private String customerName;

    private String orderNumber;
    
    private LocalDateTime orderDateTime;

    private List<Item> items;

    private String statusOrder;

    private int waitingTime;

    private long remainingWaitingTime;

    @Data
    public static class Item {
        private String name;
        private int price;
        private int quantity;
        private int cookingTime;
        private String customization;
    }
}
