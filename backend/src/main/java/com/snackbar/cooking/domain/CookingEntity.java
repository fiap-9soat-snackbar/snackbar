package com.snackbar.cooking.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CookingEntity {

    @Id
    private String id;
    
    private String cpfCliente;
    private String ClientName;
    private LocalDateTime dataHoraPedido;
    private List<Item> itens;


    public static class Item {
        private String nome;
        private int quantidade;
        private String personalizacao;
    }
}