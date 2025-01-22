 
## 📍Productsv2 Endpoints

| route               | description                                          
|----------------------|-----------------------------------------------------
| <kbd>GET /api/productsv2</kbd>     | See [request details](#get-productsv2)
| <kbd>GET /api/productsv2/id/{id}</kbd>     |  See [request details](#get-productsv2-id)
| <kbd>GET /api/products/category/{category}</kbd>     |See [request details](#get-productsv2-category)
| <kbd>POST /api/productsv2</kbd>     | See [request details](#post-productsv2)
| <kbd>PUT /api/products/id/{id}</kbd>     | See [request details](#put-productsv2)
| <kbd>DELETE /api/products/id/{id}</kbd>     | See [request details](#delete-productsv2) 


<h3 id="get-productsv2">GET /api/productsv2</h3>

**RESPONSE**  
```json
[
    {
        "id": "671bb29c52801c1c1efe6911",
        "category": "Lanche",
        "description": "Hambúrguer artesanal 160g, servido com pão de brioche, alface e tomate.",
        "name": "Hambúrguer",
        "price": 22,
        "cookingTime": 10
    }
        /* All other products */
]

```

<h3 id="get-productsv2-id">GET /api/productsv2/id/{id}</h3>

**RESPONSE**
```json
{
        "id": "671bb29c52801c1c1efe6911",
        "category": "Lanche",
        "description": "Hambúrguer artesanal 160g, servido com pão de brioche, alface e tomate.",
        "name": "Hambúrguer",
        "price": 22,
        "cookingTime": 10
}

```

<h3 id="get-productsv2-category">GET /api/productsv2/category/{category}</h3>

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
<h3 id="post-productsv2">POST /api/productsv2</h3>

**REQUEST**  
```json
{
    "name": "Hambúrguer",
    "category": "Lanche",
    "description": "Hambúrguer artesanal 160g, servido com pão de brioche, alface e tomate.",
    "price": 22,
    "cookingTime": 10
}
```
**RESPONSE**
```json
{
    "id": "671d1c91f7689b2849534586",
    "category": "Lanche",
    "description": "Hambúrguer artesanal 160g, servido com pão de brioche, alface e tomate.",
    "name": "Hambúrguer",
    "price": 22,
    "cookingTime": 10
}

```

<h3 id="put-productsv2">PUT /api/productsv2/id/{id}</h3>

**REQUEST**  
```json
{
    "id": "67266201b5ad4f0589fe6917",
    "category": "Acompanhamento",
    "description": "Porção grande de batatas fritas crocantes.",
    "name": "Batata frita Grande",
    "price": 15,
    "cookingTime": 12
}
```

**RESPONSE**  
```json
{
    "id": "67266201b5ad4f0589fe6917",
    "category": "Acompanhamento",
    "description": "Porção grande de batatas fritas crocantes.",
    "name": "Batata frita Grande",
    "price": 15,
    "cookingTime": 12
}

```
<h3 id="delete-productsv2">DELETE /api/productsv2/id/{id}</h3>

**RESPONSE**
HTTP 200 Only