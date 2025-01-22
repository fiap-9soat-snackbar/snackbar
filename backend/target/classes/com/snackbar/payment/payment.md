 
## 📍Payments Endpoints

| route               | description                                          
|----------------------|-----------------------------------------------------
| <kbd>GET /api/payments</kbd>     | See [request details](#get-payments)
| <kbd>GET /api/payments/id/{id}</kbd>     |  See [request details](#get-payments-id)
| <kbd>GET /api/payments/externalId/{externalId}</kbd>     |See [request details](#get-payments-external-id)
| <kbd>POST /api/payments</kbd>     | See [request details](#post-payments)
| <kbd>POST /api/payments/mercadopago</kbd>     | See [request details](#post-mercadopago)
| <kbd>PATCH /api/payments/updateStatusWebhook</kbd>     | See [request details](#patch-update-status-webhook) 


<h3 id="get-payments">GET /api/payments</h3>

**RESPONSE**  
```json
[
    {
        "id": "679040d918563a7865bc7ccc",
        "orderId": "679040d918563a7865bc7ccb",
        "totalDue": 10.00,
        "paymentStatus": "PAGO",
        "paymentMethod": "mercado_pago",
        "externalPaymentId": "679040d918563a7865bc7ccd"
    }
]

/* All other payments */

```

<h3 id="get-payments-id">GET /api/payments/id/{id}</h3>

**RESPONSE**
```json

{
    "id": "679040d918563a7865bc7ccc",
    "orderId": "679040d918563a7865bc7ccb",
    "totalDue": 10.00,
    "paymentStatus": "PAGO",
    "paymentMethod": "mercado_pago",
    "externalPaymentId": "679040d918563a7865bc7ccd"
}

```

<h3 id="get-payments-external-id">GET /api/payments/externalId/{externalId}</h3>

**RESPONSE**
```json
[
    {
        "id": "671d1ab834d76230acfe6911",
        "category": "Lanche",
        "description": "Hambúrguer artesanal 160g, servido com pão de brioche, alface e tomate.",
        "name": "Hambúrguer",
        "price": 22,
        "cookingTime": 10
    },
    {
        "id": "67266201b5ad4f0589fe6912",
        "category": "Lanche",
        "description": "Hambúrguer artesanal 160g, servido com pão de brioche e queijo prato.",
        "name": "Cheesebúrguer",
        "price": 25,
        "cookingTime": 10
    }
        /* All other products in the same category */
]

```
<h3 id="post-payments">POST /api/payments</h3>

**REQUEST**  
```json
{
    "orderId": "678d6a63ec453308f7087d0c",
    "paymentMethod": "Mercado_Pago"
}
```
**RESPONSE**
```json
{
    "id": "6790e5c0f168e54e6f5e9d82",
    "orderId": "678d6a63ec453308f7087d0c",
    "totalDue": 66,
    "paymentStatus": "PENDENTE",
    "paymentMethod": "Mercado_Pago",
    "externalPaymentId": "6790e5c0f168e54e6f5e9d83"
}

```

<h3 id="post-mercadopago">POST /api/payments/mercadopago</h3>

**REQUEST**  
```json
{
    "paymentId": "6790e4fef168e54e6f5e9d80",
    "totalDue": 55,
    "cpf": "01234567894",
    "callbackURL": "snackbar.com/payments/updateStatusWebhook"
}
```

**RESPONSE**  
```json
{
    "id": "6790e6faf168e54e6f5e9d84",
    "paymentId": "6790e4fef168e54e6f5e9d80",
    "totalDue": 55,
    "cpf": "01234567894",
    "callbackURL": "snackbar.com/payments/updateStatusWebhook"
}

```
<h3 id="patch-update-status-webhook">PATCH /api/payments/updateStatusWebhook</h3>

**REQUEST**  
```json
{
    "externalId": "6790e4fef168e54e6f5e9d81",
    "paymentStatus": "PAGO"
}
```

**RESPONSE**  
```json
{
    "id": "6790e4fef168e54e6f5e9d80",
    "orderId": "6790e4fef168e54e6f5e9d7f",
    "totalDue": 10.00,
    "paymentStatus": "PAGO",
    "paymentMethod": "mercado_pago",
    "externalPaymentId": "6790e4fef168e54e6f5e9d81"
}

```