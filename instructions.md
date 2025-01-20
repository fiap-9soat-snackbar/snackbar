# API Instructions

## Client Side:

### 1. Sign-up
**POST** `/api/user/auth/signup`

Request body:
```json
{
    "email": "string",
    "password": "string",
    "name": "string",
    "cpf": "string"
}
```

Response body:
```json
{
    "token": "string",
    "type": "Bearer",
    "id": "string",
    "username": "string",
    "email": "string"
}
```

### 2. Log-in
**POST** `/api/user/auth/login`

Request body:
```json
{
    "email": "string",
    "password": "string"
}
```

Response body:
```json
{
    "token": "string",
    "type": "Bearer",
    "id": "string",
    "username": "string",
    "email": "string"
}
```

### 3. Get data from all Users (Admin only)
**GET** `/api/user/`

Response body:
```json
[
    {
        "id": "string",
        "name": "string",
        "email": "string",
        "cpf": "string",
        "role": "string"
    }
]
```

### 4. Get data from a specific User by CPF (Admin only)
**GET** `/api/user/cpf/{{user_cpf}}`

Response body:
```json
{
    "id": "string",
    "name": "string",
    "email": "string",
    "cpf": "string",
    "role": "string"
}
```

### 5. Delete an User (Admin only)
**DELETE** `/api/user/{{user_id}}`

Response: 204 No Content

### 6. Get data from all Products
**GET** `/api/productsv2`

Response body:
```json
[
    {
        "id": "string",
        "name": "string",
        "description": "string",
        "category": "string",
        "price": "number",
        "image": "string"
    }
]
```

### 7. Get data from a specific Product
**GET** `/api/productsv2/id/{{product_id}}`

Response body:
```json
{
    "id": "string",
    "name": "string",
    "description": "string",
    "category": "string",
    "price": "number",
    "image": "string"
}
```

### 8. Get data from Products of specific category
**GET** `/api/productsv2/category/{{category}}`

Response body:
```json
[
    {
        "id": "string",
        "name": "string",
        "description": "string",
        "category": "string",
        "price": "number",
        "image": "string"
    }
]
```

### 9. Delete a Product (Admin only)
**DELETE** `/api/productsv2/id/{{product_id}}`

Response: 204 No Content

### 10. Create a Basket
**POST** `/api/baskets`

Request body:
```json
{
    "items": [
        {
            "productId": "string",
            "quantity": "number"
        }
    ]
}
```

Response body:
```json
{
    "id": "string",
    "items": [
        {
            "id": "string",
            "productId": "string",
            "quantity": "number"
        }
    ]
}
```

### 11. Add item to Basket
**POST** `/api/baskets/{{item_id}}/items`

Request body:
```json
{
    "productId": "string",
    "quantity": "number"
}
```

Response body:
```json
{
    "id": "string",
    "items": [
        {
            "id": "string",
            "productId": "string",
            "quantity": "number"
        }
    ]
}
```

### 12. Get data from a specific Basket
**GET** `/api/baskets/{{basket_id}}`

Response body:
```json
{
    "id": "string",
    "items": [
        {
            "id": "string",
            "productId": "string",
            "quantity": "number"
        }
    ]
}
```

### 13. Delete a Basket
**DELETE** `/api/baskets/{{basket_id}}/items/{{item_id}}`

Response: 204 No Content

### 14. Check the order out
**POST** `/api/checkout/{{order_id}}`

Response body:
```json
{
    "id": "string",
    "status": "string",
    "items": [
        {
            "productId": "string",
            "quantity": "number"
        }
    ],
    "total": "number"
}
```

### 15. Pay for the order
**POST** `/api/payments`

Request body:
```json
{
    "orderId": "string",
    "amount": "number",
    "paymentMethod": "string"
}
```

Response body:
```json
{
    "id": "string",
    "orderId": "string",
    "amount": "number",
    "status": "string",
    "externalId": "string"
}
```

### 16. Get data from all payments (Admin only)
**GET** `/api/payments`

Response body:
```json
[
    {
        "id": "string",
        "orderId": "string",
        "amount": "number",
        "status": "string",
        "externalId": "string"
    }
]
```

### 17. Get data from a specific payment (Admin only)
**GET** `/api/payments/id/{{order_id}}`

Response body:
```json
{
    "id": "string",
    "orderId": "string",
    "amount": "number",
    "status": "string",
    "externalId": "string"
}
```

### 18. Get data from an external Payment (Admin only)
**GET** `/api/payments/externalId/{{external_id}}`

Response body:
```json
{
    "id": "string",
    "orderId": "string",
    "amount": "number",
    "status": "string",
    "externalId": "string"
}
```

### 19. Update Payment status (Application internal use)
**PATCH** `/api/payments/updateStatusWebhook`

Request body:
```json
{
    "externalId": "string",
    "status": "string"
}
```

Response body:
```json
{
    "id": "string",
    "orderId": "string",
    "status": "string",
    "externalId": "string"
}
```

## Kitchen Side:

### 1. Receive an Order
**POST** `/api/cooking/receive-order/{{order_id}}`

Response body:
```json
{
    "id": "string",
    "orderId": "string",
    "status": "RECEIVED",
    "items": [
        {
            "productId": "string",
            "quantity": "number"
        }
    ]
}
```

### 2. Start Preparation
**POST** `/api/cooking/start-preparation/{{order_id}}`

Response body:
```json
{
    "id": "string",
    "orderId": "string",
    "status": "IN_PREPARATION",
    "items": [
        {
            "productId": "string",
            "quantity": "number"
        }
    ]
}
```

### 3. Finish Preparation
**POST** `/api/cooking/finish-preparation/{{order_id}}`

Response body:
```json
{
    "id": "string",
    "orderId": "string",
    "status": "READY",
    "items": [
        {
            "productId": "string",
            "quantity": "number"
        }
    ]
}
```

### 4. Check the preparation status of a specific order which was already received
**GET** `/api/cooking/{{order_id}}`

Response body:
```json
{
    "id": "string",
    "orderId": "string",
    "status": "string",
    "items": [
        {
            "productId": "string",
            "quantity": "number"
        }
    ]
}
```

### 5. Check the preparation status of all orders which were already received
**GET** `/api/cooking`

Response body:
```json
[
    {
        "id": "string",
        "orderId": "string",
        "status": "string",
        "items": [
            {
                "productId": "string",
                "quantity": "number"
            }
        ]
    }
]
```

### 6. Notify customer about order readiness
**POST** `/api/pickup/notify/{{order_id}}`

Response body:
```json
{
    "id": "string",
    "orderId": "string",
    "status": "CUSTOMER_NOTIFIED"
}
```

### 7. Deliver order
**POST** `/api/pickup/delivery/{{order_id}}`

Response body:
```json
{
    "id": "string",
    "orderId": "string", 
    "status": "DELIVERED"
}
```