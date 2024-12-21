 
## 📍Orders Endpoints

| route               | description                                          
|----------------------|-----------------------------------------------------
| <kbd>GET /api/orders</kbd>     | See [request details](#get-orders)
| <kbd>GET /api/orders/{id}</kbd>     |  See [request details](#get-orders-id)
| <kbd>GET /api/orders/number/{orderNumber}</kbd>     |See [request details](#get-orders-ordernumber)
| <kbd>POST /api/orders</kbd>     | See [request details](#post-orders)
| <kbd>PUT /api/orders/{id}</kbd>     | See [request details](#put-orders)


<h3 id="get-orders">GET /api/orders</h3>

**RESPONSE**  
```json
[
    {
        "id": "672662025524c80e99fe6911",
        "orderNumber": "000001",
        "orderDateTime": "2024-10-31T04:52:17.367Z",
        "cpf": "12345",
        "name": "Jose Moreira",
        "items": [
            {
                "name": "Hot Dog de Salsicha",
                "quantity": 1,
                "price": 20,
                "cookingTime": 10,
                "customization": "Molho extra"
            }
        ],
        "statusOrder": "NOVO",
        "paymentMethod": null,
        "totalPrice": 20,
        "remainingTime": 0,
        "waitingTime": 10
    }
    /* All other orders */
]
```

<h3 id="get-orders-id">GET /api/orders/{id}</h3>

**RESPONSE**
```json
{
    "id": "672662025524c80e99fe6911",
    "orderNumber": "000001",
    "orderDateTime": "2024-10-31T04:52:17.367Z",
    "cpf": "12345",
    "name": "Jose Moreira",
    "items": [
        {
            "name": "Hot Dog de Salsicha",
            "quantity": 1,
            "price": 20,
            "cookingTime": 10,
            "customization": "Molho extra"
        }
    ],
    "statusOrder": "NOVO",
    "paymentMethod": null,
    "totalPrice": 20,
    "remainingTime": 0,
    "waitingTime": 10
}
```

<h3 id="get-orders-ordernumber">GET /api/orders/number/{orderNumber}</h3>

**RESPONSE**
```json
{
    "id": "672662025524c80e99fe6911",
    "orderNumber": "000001",
    "orderDateTime": "2024-10-31T04:52:17.367Z",
    "cpf": "12345",
    "name": "Jose Moreira",
    "items": [
        {
            "name": "Hot Dog de Salsicha",
            "quantity": 1,
            "price": 20,
            "cookingTime": 10,
            "customization": "Molho extra"
        }
    ],
    "statusOrder": "NOVO",
    "paymentMethod": null,
    "totalPrice": 20,
    "remainingTime": 0,
    "waitingTime": 10
}
```
<h3 id="post-orders">POST /api/orders</h3>

**REQUEST**  
```json
{
    "cpf": "12347",
    "name": "Fulano de Souza", // Optional field
    "items": [ //Product names must follow existing items in Products collection (see GET /api/products)
        {
            "name": "Pizza de Calabresa",
            "quantity": 1,
            "customization": "queijo extra" // Optional field
        },
        {
            "name": "Coca-Cola 350ml",
            "quantity": 2
        },
        {
            "name": "Sundae de Chocolate",
            "quantity": 3
        }
    ]
}
```
**RESPONSE**
```json
{
    "id": "67266dffb6941f69258b3919",
    "orderNumber": "000002",
    "orderDateTime": "2024-11-02T18:22:55.239740534Z",
    "cpf": "12347",
    "name": "Fulano de Souza",
    "items": [
        {
            "name": "Pizza de Calabresa",
            "quantity": 1,
            "price": 25,
            "cookingTime": 12,
            "customization": "queijo extra"
        },
        {
            "name": "Coca-Cola 350ml",
            "quantity": 2,
            "price": 8,
            "cookingTime": 1,
            "customization": null
        },
        {
            "name": "Sundae de Chocolate",
            "quantity": 3,
            "price": 10,
            "cookingTime": 5,
            "customization": null
        }
    ],
    "statusOrder": "NOVO",
    "paymentMethod": null,
    "totalPrice": 71,
    "remainingTime": 0,
    "waitingTime": 29
}
```

<h3 id="put-products">PUT /api/products/{id}</h3>

**REQUEST**  
```json
{
  "id": "672662a6b6941f69258b3918",
  "items": [
    {
      "name": "Coca-Cola 350ml",
      "quantity": 2,
      "customization": "1 das cocas sem gelo"
    }
  ]
}
```

**RESPONSE**  
```json
{
    "id": "672662a6b6941f69258b3918",
    "orderNumber": "000002",
    "orderDateTime": "2024-11-02T17:34:30.979Z",
    "cpf": "12347",
    "name": "Fulano de Souza",
    "items": [
        {
            "name": "Coca-Cola 350ml",
            "quantity": 2,
            "price": 8,
            "cookingTime": 1,
            "customization": "1 das cocas sem gelo"
        }
    ],
    "statusOrder": "NOVO",
    "paymentMethod": null,
    "totalPrice": 16,
    "remainingTime": 0,
    "waitingTime": 2
}
```
