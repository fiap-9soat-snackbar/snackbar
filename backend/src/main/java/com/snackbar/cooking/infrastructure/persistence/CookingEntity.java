package com.snackbar.cooking.infrastructure.persistence;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "cookings")
public class CookingEntity {

    @Id
    private String id;

    @Field("orderId")
    private String orderId;
    
    private Enum status;

    public CookingEntity(String id, String orderId, Enum status) {
        this.id = id;
        this.orderId = orderId;
        this.status = status;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Enum getStatus() {
        return status;
    }

    public void setStatus(Enum status) {
        this.status = status;
    }
}