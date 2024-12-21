package com.snackbar.cooking.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.time.Duration;
import java.util.List;
import lombok.Data;

@Data
@Document(collection = "orders")
public class Cooking {

    @Id
    private String id;

    private String name;

    private String cpf;

    private String orderNumber;
    private LocalDateTime orderDateTime;
    private List<Item> items;
    private String statusOrder;
    private int waitingTime;
    private long remainingWaitingTime;
    private String paymentMethod;
    private int totalPrice;

    public void updateRemainingWaitingTime() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime deliverTime = orderDateTime.plusMinutes(waitingTime);
        remainingWaitingTime = Duration.between(currentDateTime, deliverTime).toMinutes();
        remainingWaitingTime = Math.max(remainingWaitingTime, 0);
    }

    @Data
    public static class Item {
        private String name;
        private int price;
        private int quantity;
        private int cookingTime;
        private String customization;
    }
}
