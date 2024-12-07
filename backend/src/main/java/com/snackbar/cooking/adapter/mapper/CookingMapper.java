package com.snackbar.cooking.adapter.mapper;

import com.snackbar.cooking.domain.entity.CookingEntity;
import com.snackbar.cooking.adapter.persistence.model.CookingDocument;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;

@Component
public class CookingMapper {
    public CookingEntity toEntity(CookingDocument document) {
        if (document == null) return null;
        
        return CookingEntity.builder()
                .id(document.getId())
                .orderNumber(document.getOrderNumber())
                .name(document.getName())
                .statusOrder(document.getStatusOrder())
                .entryTime(document.getEntryTime())
                .exitTime(document.getExitTime())
                .preparationTime(document.getPreparationTime())
                .items(document.getItems().stream()
                        .map(this::toEntityItem)
                        .collect(Collectors.toList()))
                .build();
    }

    public CookingDocument toDocument(CookingEntity entity) {
        if (entity == null) return null;

        return CookingDocument.builder()
                .id(entity.getId())
                .orderNumber(entity.getOrderNumber())
                .name(entity.getName())
                .statusOrder(entity.getStatusOrder())
                .entryTime(entity.getEntryTime())
                .exitTime(entity.getExitTime())
                .preparationTime(entity.getPreparationTime())
                .items(entity.getItems().stream()
                        .map(this::toDocumentItem)
                        .collect(Collectors.toList()))
                .build();
    }

    private CookingEntity.Item toEntityItem(CookingDocument.Item item) {
        return CookingEntity.Item.builder()
                .name(item.getName())
                .quantity(item.getQuantity())
                .observation(item.getObservation())
                .build();
    }

    private CookingDocument.Item toDocumentItem(CookingEntity.Item item) {
        return CookingDocument.Item.builder()
                .name(item.getName())
                .quantity(item.getQuantity())
                .observation(item.getObservation())
                .build();
    }
}