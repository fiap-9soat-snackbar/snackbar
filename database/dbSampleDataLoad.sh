#!/bin/bash

set -e

echo Creating database User

mongosh $MONGO_INITDB_ROOT_USERNAME:$MONGO_INITDB_ROOT_PASSWORD@localhost:$DB_PORT <<EOF

use admin
db.createUser({
  user: '$MONGODB_USER',
  pwd:  '$MONGODB_PASSWORD',
  roles: [{
    role: 'readWrite',
    db: '$MONGO_INITDB_DATABASE'
  }]
})
EOF

#All product data in this file is sample for test purpose only

echo Loading Products collection

mongosh $MONGO_INITDB_ROOT_USERNAME:$MONGO_INITDB_ROOT_PASSWORD@localhost:$DB_PORT <<EOF

use snackbar
db.products.insertMany( [
   {
      name: 'Hambúrguer',
      category: 'Lanche',
      description: 'Hambúrguer artesanal 160g, servido com pão de brioche, alface e tomate.',
      price: 30.00,
      cookingTime: 10,
      image: 'https://example.com/hamburguer.jpg',
      _class: 'com.example.snackbar.domain.Product'
   },
   {
      name: 'Batata frita',
      category: 'Acompanhamento',
      description: 'Batatas fritas crocantes com ketchup.',
      price: 15.00,
      cookingTime: 8,
      image: 'https://example.com/batata-frita.jpg',
      _class: 'com.example.snackbar.domain.Product'
   },
   {
      name: 'Refrigerante',
      category: 'Bebida',
      description: 'Refrigerante de cola 300ml.',
      price: 8.00,
      cookingTime: 1,
      image: 'https://example.com/refrigerante.jpg',
      _class: 'com.example.snackbar.domain.Product'
   },
   {
      name: 'Sorvete',
      category: 'Sobremesa',
      description: 'Sorvete de chocolate com calda de caramelo.',
      price: 12.00,
      cookingTime: 5,
      image: 'https://example.com/sorvete.jpg',
      _class: 'com.example.snackbar.domain.Product'
   },
   {
      name: 'Pizza',
      category: 'Lanche',
      description: 'Pizza de mussarela com tomate e manjericão.',
      price: 25.00,
      cookingTime: 12,
      image: 'https://example.com/pizza.jpg',
      _class: 'com.example.snackbar.domain.Product'
   },
   {
      name: 'Chips',
      category: 'Acompanhamento',
      description: 'Chips de batata doce.',
      price: 18.00,
      cookingTime: 1,
      image: 'https://example.com/chips.jpg',
      _class: 'com.example.snackbar.domain.Product'
   },
   {
      name: 'Água',
      category: 'Bebida',
      description: 'Água mineral 500ml.',
      price: 5.00,
      cookingTime: 1,
      image: 'https://example.com/agua.jpg',
      _class: 'com.example.snackbar.domain.Product'
   },
   {
      name: 'Brownie',
      category: 'Sobremesa',
      description: 'Brownie de chocolate com cobertura de caramelo.',
      price: 15.00,
      cookingTime: 2,
      image: 'https://example.com/brownie.jpg',
      _class: 'com.example.snackbar.domain.Product'
   },
   {
      name: 'Salada',
      category: 'Lanche',
      description: 'Salada de alface, tomate e manjericão.',
      price: 20.00,
      cookingTime: 5,
      image: 'https://example.com/salada.jpg',
      _class: 'com.example.snackbar.domain.Product'
   }
] )
EOF

#All orders data in this file, specially customer names and CPFs, is sample for test purpose only

echo Loading Orders collection

mongosh $MONGO_INITDB_ROOT_USERNAME:$MONGO_INITDB_ROOT_PASSWORD@localhost:$DB_PORT <<EOF

use snackbar
db.orders.insertMany([
  {
    id: "1234567890abcdef12345678",
    client_cpf: "123.456.789-00",
    client_name: "João Silva",
    dataHoraPedido: "2023-05-10T14:30:00",
    items: [
      { name: "X-Burger", quantity: 2, customization: "Sem cebola" },
      { name: "Batata Frita", quantity: 1, customization: "Tamanho grande" }
    ],
    status: "Recebido"
  },
  {
    id: "234567890abcdef123456789",
    client_cpf: "987.654.321-00",
    client_name: "Maria Oliveira",
    dataHoraPedido: "2023-05-10T15:45:00",
    items: [
      { name: "Pizza Margherita", quantity: 1, customization: "Borda recheada" },
      { name: "Refrigerante", quantity: 2, customization: "Sem gelo" }
    ],
    status: "Em preparação"
  },
  {
    id: "345678901abcdef234567890",
    client_cpf: "456.789.012-34",
    client_name: "Carlos Santos",
    dataHoraPedido: "2023-05-10T16:20:00",
    items: [
      { name: "Salada Caesar", quantity: 1, customization: "Sem croutons" },
      { name: "Suco Natural", quantity: 1, customization: "Laranja com morango" }
    ],
    status: "Pronto"
  },
  {
    id: "456789012abcdef345678901",
    client_cpf: "789.012.345-67",
    client_name: "Ana Ferreira",
    dataHoraPedido: "2023-05-10T17:10:00",
    items: [
      { name: "Prato do Dia", quantity: 2, customization: "Vegetariano" },
      { name: "Sobremesa", quantity: 2, customization: "Pudim" }
    ],
    status: "Finalizado"
  },
  {
    id: "567890123abcdef456789012",
    client_cpf: "012.345.678-90",
    client_name: "Pedro Almeida",
    dataHoraPedido: "2023-05-10T18:00:00",
    items: [
      { name: "Hot Dog", quantity: 3, customization: "Molho extra" },
      { name: "Milkshake", quantity: 1, customization: "Chocolate com menta" }
    ],
    status: "Finalizado"
},
{
    "id": "67230df5305e6e531c4149a2",
    "orderNumber": 10,
    "orderDateTime": "2024-10-31T04:52:17.367Z",
    "customerId": 12345.0,
    "clientName": "Jose Moreira",
    "items": [
        {
            "name": "Hot Dog",
            "price": 3,
            "quantity": 3,
            "cookingTime": 10,
            "customization": "Molho extra"
        }
    ],
    "statusOrder": "NOVO",
    "waitingTime": 10,
    "totalPrice": 3.0
},
{
    "id": "67230e25305e6e531c4149a3",
    "orderNumber": 10,
    "orderDateTime": "2024-10-31T04:52:17.367Z",
    "customerId": 12347.0,
    "clientName": "Rosangela Santos",
    "items": [
        {
            "name": "Hot Dog",
            "price": 3,
            "quantity": 3,
            "cookingTime": 10,
            "customization": "Molho extra"
        }
    ],
    "statusOrder": "NOVO",
    "waitingTime": 10,
    "totalPrice": 3.0
}
  ])
EOF