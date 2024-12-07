package com.snackbar.cooking.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CookingEntity {
    private String id;
    private String orderNumber;
    private String name;
    private String statusOrder;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private String preparationTime;
    private List<Item> items;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Item {
        private String name;
        private Integer quantity;
        private String observation;
    }
}