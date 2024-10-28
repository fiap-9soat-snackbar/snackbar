package com.snackbar.cooking.domain;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
@Document(collection = "orders")
public class Cooking {

    @Id
    private String id;
    
    @Field("client_cpf")
    private String cpfCliente;

    @Field("client_name")
    private String clientName;
    
    private LocalDateTime dataHoraPedido;

    private List<Item> items;

    private String status;

    @Data
    public static class Item {
        private String name;
        private int quantity;
        private String customization;
    }
}
