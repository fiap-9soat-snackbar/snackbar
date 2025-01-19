# API Endpoints Documentation

## Basket Service Endpoints (`/api/baskets`)

### Create Basket
- **Method**: POST
- **Endpoint**: `/api/baskets`
- **Request Body**:
```json
{
    "userId": "string"
}
```
- **Response Body**:
```json
{
    "id": "string",
    "userId": "string",
    "items": []
}
```

### Get Basket
- **Method**: GET
- **Endpoint**: `/api/baskets/{basketId}`
- **Response Body**:
```json
{
    "id": "string",
    "userId": "string",
    "items": [
        {
            "id": "string",
            "productId": "string",
            "quantity": "integer"
        }
    ]
}
```

### Add Item to Basket
- **Method**: POST
- **Endpoint**: `/api/baskets/{basketId}/items`
- **Request Body**:
```json
{
    "productId": "string",
    "quantity": "integer"
}
```

### Delete Item from Basket
- **Method**: DELETE
- **Endpoint**: `/api/baskets/{basketId}/items/{itemId}`

## Cooking Service Endpoints (`/api/cooking`)

### Receive Order
- **Method**: POST
- **Endpoint**: `/api/cooking/receive-order/{id}`
- **Response Body**:
```json
{
    "id": "string",
    "orderId": "string",
    "status": "string",
    "startTime": "datetime",
    "endTime": "datetime"
}
```

### Start Preparation
- **Method**: POST
- **Endpoint**: `/api/cooking/start-preparation/{id}`

### Finish Preparation
- **Method**: POST
- **Endpoint**: `/api/cooking/finish-preparation/{id}`

### Get All Cookings
- **Method**: GET
- **Endpoint**: `/api/cooking`

### Get Cooking by Order ID
- **Method**: GET
- **Endpoint**: `/api/cooking/{id}`

## Order Service Endpoints (`/api/orders`)

### Create Order
- **Method**: POST
- **Endpoint**: `/api/orders`
- **Request Body**:
```json
{
    "customerName": "string",
    "items": [
        {
            "productId": "string",
            "quantity": "integer"
        }
    ]
}
```

### Update Order
- **Method**: PUT
- **Endpoint**: `/api/orders`
- **Request Body**:
```json
{
    "id": "string",
    "status": "string",
    "items": [
        {
            "productId": "string",
            "quantity": "integer"
        }
    ]
}
```

### List Orders
- **Method**: GET
- **Endpoint**: `/api/orders`

### Get Order by ID
- **Method**: GET
- **Endpoint**: `/api/orders/{id}`

### Get Order by Number
- **Method**: GET
- **Endpoint**: `/api/orders/number/{orderNumber}`

## Payment Service Endpoints (`/api/payments`)

### Create Payment
- **Method**: POST
- **Endpoint**: `/api/payments`
- **Request Body**:
```json
{
    "orderId": "string",
    "amount": "number",
    "paymentMethod": "string"
}
```
- **Response Body**:
```json
{
    "id": "string",
    "orderId": "string",
    "amount": "number",
    "status": "string",
    "createdAt": "datetime"
}
```

### Create MercadoPago Payment
- **Method**: POST
- **Endpoint**: `/api/payments/mercadopago`
- **Request Body**:
```json
{
    "orderId": "string",
    "amount": "number"
}
```

### List Payments
- **Method**: GET
- **Endpoint**: `/api/payments`

### Get Payment by ID
- **Method**: GET
- **Endpoint**: `/api/payments/id/{id}`

### Get Payment by External ID
- **Method**: GET
- **Endpoint**: `/api/payments/externalId/{externalId}`

### Update Payment Status (Webhook)
- **Method**: PATCH
- **Endpoint**: `/api/payments/updateStatusWebhook`
- **Request Body**:
```json
{
    "externalId": "string",
    "status": "string"
}
```

## Pickup Service Endpoints (`/api/pickup`)

### Notify Customer
- **Method**: POST
- **Endpoint**: `/api/pickup/notify/{orderId}`

### Delivery Order
- **Method**: POST
- **Endpoint**: `/api/pickup/delivery/{orderId}`

## Product Service Endpoints (`/api/products`)

### Create Product
- **Method**: POST
- **Endpoint**: `/api/products`
- **Request Body**:
```json
{
    "name": "string",
    "description": "string",
    "price": "number",
    "category": "string"
}
```

### Get Product
- **Method**: GET
- **Endpoint**: `/api/products/{id}`

### Get All Products
- **Method**: GET
- **Endpoint**: `/api/products`

### Get Products by Category
- **Method**: GET
- **Endpoint**: `/api/products/category/{category}`

### Update Product
- **Method**: PUT
- **Endpoint**: `/api/products/{id}`
- **Request Body**:
```json
{
    "name": "string",
    "description": "string",
    "price": "number",
    "category": "string"
}
```

### Delete Product
- **Method**: DELETE
- **Endpoint**: `/api/products/{id}`