package com.snackbar.cooking.infrastructure.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "cooking")
public class CookingEntity {
    @Id
    private String id;
    private String orderNumber;
    private String statusOrder;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<ItemEntity> items;
    private Integer preparationTime;
    private LocalDateTime startedAt;
    private LocalDateTime finishedAt;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ItemEntity {
        private String productId;
        private String name;
        private Integer quantity;
        private Double price;
    }
}