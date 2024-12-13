package com.snackbar.cooking.domain.model;

import lombok.Builder;
import lombok.Value;
import java.time.LocalDateTime;
import java.util.List;

@Value
@Builder
public class Cooking {
    String id;
    String orderNumber;
    String statusOrder;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    List<Item> items;
    Integer preparationTime;
    LocalDateTime startedAt;
    LocalDateTime finishedAt;

    @Value
    @Builder
    public static class Item {
        String productId;
        String name;
        Integer quantity;
        Double price;
    }
}