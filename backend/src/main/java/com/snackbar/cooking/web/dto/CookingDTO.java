package com.snackbar.cooking.web.dto;

import lombok.Builder;
import lombok.Value;
import java.time.LocalDateTime;
import java.util.List;

@Value
@Builder
public class CookingDTO {
    String id;
    String orderNumber;
    String statusOrder;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    List<ItemDTO> items;
    Integer preparationTime;
    LocalDateTime startedAt;
    LocalDateTime finishedAt;

    @Value
    @Builder
    public static class ItemDTO {
        String productId;
        String name;
        Integer quantity;
        Double price;
    }
}