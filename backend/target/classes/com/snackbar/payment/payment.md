## 📍Payment Endpoints

| route               | description                                          
|----------------------|-----------------------------------------------------
| `GET /api/payments`     | Get all payments
| `GET /api/payments/id/{id}`     | Get payment by ID
| `GET /api/payments/externalId/{externalId}`     | Get payment by external ID
| `POST /api/payments`     | Create a new payment
| `POST /api/payments/mercadopago`     | Create a new MercadoPago payment
| `PATCH /api/payments/updateStatusWebhook`     | Update payment status via webhook

### GET /api/payments

List all payments in the system.

**RESPONSE**
```json
[
    {
        "id": "678d69232673ebea95fe6912",
        "orderId": "order123",
        "totalDue": 100.00,
        "paymentStatus": "PENDING",
        "paymentMethod": "CREDIT_CARD",
        "externalPaymentId": "ext123"
    }
]
```

### GET /api/payments/id/{id}

Get payment details by payment ID.

**RESPONSE**
```json
{
    "id": "678d69232673ebea95fe6912",
    "orderId": "order123",
    "totalDue": 100.00,
    "paymentStatus": "PENDING",
    "paymentMethod": "CREDIT_CARD",
    "externalPaymentId": "ext123"
}
```

### GET /api/payments/externalId/{externalId}

Get payment details by external payment ID.

**RESPONSE**
```json
{
    "id": "678d69232673ebea95fe6912",
    "orderId": "order123",
    "totalDue": 100.00,
    "paymentStatus": "PENDING",
    "paymentMethod": "CREDIT_CARD",
    "externalPaymentId": "ext123"
}
```

### POST /api/payments

Create a new payment.

**REQUEST**
```json
{
    "orderId": "order123",
    "paymentMethod": "CREDIT_CARD"
}
```

**RESPONSE**
```json
{
    "id": "678d69232673ebea95fe6912",
    "orderId": "order123",
    "totalDue": 100.00,
    "paymentStatus": "PENDING",
    "paymentMethod": "CREDIT_CARD",
    "externalPaymentId": "ext123"
}
```

### POST /api/payments/mercadopago

Create a new payment using MercadoPago.

**REQUEST**
```json
{
    "orderId": "order123",
    "paymentMethod": "MERCADO_PAGO"
}
```

**RESPONSE**
```json
{
    "id": "678d69232673ebea95fe6912",
    "orderId": "order123",
    "totalDue": 100.00,
    "paymentStatus": "PENDING",
    "paymentMethod": "MERCADO_PAGO",
    "externalPaymentId": "ext123"
}
```

### PATCH /api/payments/updateStatusWebhook

Update payment status via webhook (for external payment providers).

**REQUEST**
```json
{
    "externalId": "ext123",
    "status": "COMPLETED"
}
```

**RESPONSE**
```json
{
    "id": "678d69232673ebea95fe6912",
    "orderId": "order123",
    "totalDue": 100.00,
    "paymentStatus": "COMPLETED",
    "paymentMethod": "CREDIT_CARD",
    "externalPaymentId": "ext123"
}
```