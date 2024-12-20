 
## 📍Orders Endpoints

| route               | description                                          
|----------------------|-----------------------------------------------------
| <kbd>GET /api/cooking</kbd>     | See [request details](#cooking)
| <kbd>GET /api/cooking/get-order-by-id/{id}</kbd>     |  See [request details](#get-order-by-id)
| <kbd>GET /api/cooking/get-order-by-number/{order_number}</kbd>     |See [request details](#get-order-by-number)
| <kbd>PUT /api/cooking/receive-order/{id}</kbd>     | See [request details](#receive-order)
| <kbd>PUT /api/cooking/start-preparation/{id}</kbd>     | See [request details](#start-preparation)
| <kbd>PUT /api/cooking/finish-preparation/{id}</kbd>     | See [request details](#finish-preparation)


<h3 id="cooking">GET /api/cooking</h3>

**RESPONSE**  
```json
[
    {
        "id": "6726d7cdb6db288888fe6911",
        "customerName": "Jose Moreira",
        "orderNumber": "000001",
        "orderDateTime": "2024-11-03T01:00:25.14",
        "items": [
            {
                "name": "Hot Dog de Salsicha",
                "price": 20,
                "quantity": 1,
                "cookingTime": 10,
                "customization": "Molho extra"
            }
        ],
        "statusOrder": "PREPARACAO",
        "waitingTime": 10,
        "remainingWaitingTime": 6
    },
    /* All other orders */
]
```

<h3 id="get-order-by-id">GET /api/cooking/get-order-by-id/{id}</h3>

**RESPONSE**
```json
[
    {
        "id": "6726d7cdb6db288888fe6911",
        "customerName": "Jose Moreira",
        "orderNumber": "000001",
        "orderDateTime": "2024-11-03T01:00:25.14",
        "items": [
            {
                "name": "Hot Dog de Salsicha",
                "price": 20,
                "quantity": 1,
                "cookingTime": 10,
                "customization": "Molho extra"
            }
        ],
        "statusOrder": "PREPARACAO",
        "waitingTime": 10,
        "remainingWaitingTime": 6
    },
    /* All other orders */
]
```

<h3 id="get-order-by-number">GET /api/cooking/get-order-by-number/{order_number}</h3>

**RESPONSE**
```json
[
    {
        "id": "6726d7cdb6db288888fe6911",
        "customerName": "Jose Moreira",
        "orderNumber": "000001",
        "orderDateTime": "2024-11-03T01:00:25.14",
        "items": [
            {
                "name": "Hot Dog de Salsicha",
                "price": 20,
                "quantity": 1,
                "cookingTime": 10,
                "customization": "Molho extra"
            }
        ],
        "statusOrder": "PREPARACAO",
        "waitingTime": 10,
        "remainingWaitingTime": 6
    },
    /* All other orders */
]
```

<h3 id="receive-order">PUT /api/cooking/receive-order/{id}</h3>

**RESPONSE**  
```json
   Pedido recebido
```

<h3 id="start-preparation">PUT /api/cooking/start-preparation/{id}</h3>

**RESPONSE**  
```json
   Preparo iniciado
```

<h3 id="finish-preparation">PUT /api/cooking/finish-preparation/{id}</h3>

**RESPONSE**  
```json
   Preparo pronto
```