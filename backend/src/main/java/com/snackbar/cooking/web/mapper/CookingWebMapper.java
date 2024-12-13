package com.snackbar.cooking.web.mapper;

import com.snackbar.cooking.domain.model.Cooking;
import com.snackbar.cooking.web.dto.CookingDTO;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CookingWebMapper {
    
    public CookingDTO toDTO(Cooking cooking) {
        if (cooking == null) {
            return null;
        }
        return CookingDTO.builder()
                .id(cooking.getId())
                .orderNumber(cooking.getOrderNumber())
                .statusOrder(cooking.getStatusOrder())
                .createdAt(cooking.getCreatedAt())
                .updatedAt(cooking.getUpdatedAt())
                .items(mapItems(cooking.getItems()))
                .preparationTime(cooking.getPreparationTime())
                .startedAt(cooking.getStartedAt())
                .finishedAt(cooking.getFinishedAt())
                .build();
    }

    private List<CookingDTO.ItemDTO> mapItems(List<Cooking.Item> items) {
        if (items == null) {
            return null;
        }
        return items.stream()
                .map(item -> CookingDTO.ItemDTO.builder()
                        .productId(item.getProductId())
                        .name(item.getName())
                        .quantity(item.getQuantity())
                        .price(item.getPrice())
                        .build())
                .collect(Collectors.toList());
    }

    public Cooking toDomain(CookingDTO dto) {
        if (dto == null) {
            return null;
        }
        return Cooking.builder()
                .id(dto.getId())
                .orderNumber(dto.getOrderNumber())
                .statusOrder(dto.getStatusOrder())
                .createdAt(dto.getCreatedAt())
                .updatedAt(dto.getUpdatedAt())
                .items(mapDomainItems(dto.getItems()))
                .preparationTime(dto.getPreparationTime())
                .startedAt(dto.getStartedAt())
                .finishedAt(dto.getFinishedAt())
                .build();
    }

    private List<Cooking.Item> mapDomainItems(List<CookingDTO.ItemDTO> items) {
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