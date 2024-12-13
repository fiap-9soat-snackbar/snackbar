package com.snackbar.cooking.infrastructure.persistence.mapper;

import com.snackbar.cooking.domain.model.Cooking;
import com.snackbar.cooking.infrastructure.persistence.entity.CookingEntity;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CookingPersistenceMapper {
    
    public CookingEntity toEntity(Cooking cooking) {
        if (cooking == null) {
            return null;
        }
        return CookingEntity.builder()
                .id(cooking.getId())
                .orderNumber(cooking.getOrderNumber())
                .statusOrder(cooking.getStatusOrder())
                .createdAt(cooking.getCreatedAt())
                .updatedAt(cooking.getUpdatedAt())
                .items(mapToEntityItems(cooking.getItems()))
                .preparationTime(cooking.getPreparationTime())
                .startedAt(cooking.getStartedAt())
                .finishedAt(cooking.getFinishedAt())
                .build();
    }

    public Cooking toDomain(CookingEntity entity) {
        if (entity == null) {
            return null;
        }
        return Cooking.builder()
                .id(entity.getId())
                .orderNumber(entity.getOrderNumber())
                .statusOrder(entity.getStatusOrder())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .items(mapToDomainItems(entity.getItems()))
                .preparationTime(entity.getPreparationTime())
                .startedAt(entity.getStartedAt())
                .finishedAt(entity.getFinishedAt())
                .build();
    }

    private List<CookingEntity.ItemEntity> mapToEntityItems(List<Cooking.Item> items) {
        if (items == null) {
            return null;
        }
        return items.stream()
                .map(item -> CookingEntity.ItemEntity.builder()
                        .productId(item.getProductId())
                        .name(item.getName())
                        .quantity(item.getQuantity())
                        .price(item.getPrice())
                        .build())
                .collect(Collectors.toList());
    }

    private List<Cooking.Item> mapToDomainItems(List<CookingEntity.ItemEntity> items) {
        if (items == null) {
            return null;
        }
        return items.stream()
                .map(item -> Cooking.Item.builder()
                        .productId(item.getProductId())
                        .name(item.getName())
                        .quantity(item.getQuantity())
                        .price(item.getPrice())
                        .build())
                .collect(Collectors.toList());
    }
}