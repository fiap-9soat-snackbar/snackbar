package com.snackbar.basket.domain.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public record Basket(
        String id,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant basketDateTime,
        String cpf,
        String name,
        List<Item> items,
        BigDecimal totalPrice
) {}
