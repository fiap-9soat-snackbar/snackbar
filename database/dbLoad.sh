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

echo Loading Products collection

mongosh $MONGO_INITDB_ROOT_USERNAME:$MONGO_INITDB_ROOT_PASSWORD@localhost:$DB_PORT <<EOF

use snackbar
db.products.insertMany( [
   {
      name: 'Hambúrguer',
      category: 'Lanche',
      description: 'Hambúrguer artesanal 160g, servido com pão de brioche, alface e tomate.',
      price: 30.00,
      image: 'https://example.com/hamburguer.jpg',
      _class: 'com.example.snackbar.domain.Product'
   },
   {
      name: 'Batata frita',
      category: 'Acompanhamento',
      description: 'Batatas fritas crocantes com ketchup.',
      price: 15.00,
      image: 'https://example.com/batata-frita.jpg',
      _class: 'com.example.snackbar.domain.Product'
   },
   {
      name: 'Refrigerante',
      category: 'Bebida',
      description: 'Refrigerante de cola 300ml.',
      price: 8.00,
      image: 'https://example.com/refrigerante.jpg',
      _class: 'com.example.snackbar.domain.Product'
   },
   {
      name: 'Sorvete',
      category: 'Sobremesa',
      description: 'Sorvete de chocolate com calda de caramelo.',
      price: 12.00,
      image: 'https://example.com/sorvete.jpg',
      _class: 'com.example.snackbar.domain.Product'
   },
   {
      name: 'Pizza',
      category: 'Lanche',
      description: 'Pizza de mussarela com tomate e manjericão.',
      price: 25.00,
      image: 'https://example.com/pizza.jpg',
      _class: 'com.example.snackbar.domain.Product'
   },
   {
      name: 'Chips',
      category: 'Acompanhamento',
      description: 'Chips de batata doce.',
      price: 18.00,
      image: 'https://example.com/chips.jpg',
      _class: 'com.example.snackbar.domain.Product'
   },
   {
      name: 'Água',
      category: 'Bebida',
      description: 'Água mineral 500ml.',
      price: 5.00,
      image: 'https://example.com/agua.jpg',
      _class: 'com.example.snackbar.domain.Product'
   },
   {
      name: 'Brownie',
      category: 'Sobremesa',
      description: 'Brownie de chocolate com cobertura de caramelo.',
      price: 15.00,
      image: 'https://example.com/brownie.jpg',
      _class: 'com.example.snackbar.domain.Product'
   },
   {
      name: 'Salada',
      category: 'Lanche',
      description: 'Salada de alface, tomate e manjericão.',
      price: 20.00,
      image: 'https://example.com/salada.jpg',
      _class: 'com.example.snackbar.domain.Product'
   }
] )
EOF

echo Loading Orders collection

mongosh $MONGO_INITDB_ROOT_USERNAME:$MONGO_INITDB_ROOT_PASSWORD@localhost:$DB_PORT <<EOF

use snackbar
db.orders.insertMany([
    {
      "_id": ObjectId(),  
      "client_cpf": "12345678900",  
      "client_name": "João Silva",  
      "order_datetime": ISODate("2024-10-07T15:30:00Z"),
      "status": "delivered",  
      "order_number":"1",
      "items": [
        {
          "name": "Hambúrguer",
          "quantity": 1,
          "customization": "Sem picles"
        },
        {
          "name": "Batata Frita",
          "quantity": 1,
          "customization": "Extra sal"
        }
      ]
    },
    {
      "_id": ObjectId(),  
      "client_cpf": "98765432100",  
      "client_name": "Maria Souza",  
      "order_datetime": ISODate("2024-10-07T16:00:00Z"),
      "status": "pending",    
      "order_number":"2",
      "items": [
        {
          "name": "Pizza Margherita",
          "quantity": 1,
          "customization": "Sem tomate"
        },
        {
          "name": "Refrigerante",
          "quantity": 1,
          "customization": "Com gelo"
        }
      ]
    },
    {
      "_id": ObjectId(),  
      "client_cpf": "11122233344",  
      "client_name": "Carlos Pereira",  
      "order_datetime": ISODate("2024-10-07T17:15:00Z"),
      "status": "cooking",  
      "order_number":"3",
      "items": [
        {
          "name": "Sanduíche Natural",
          "quantity": 2,
          "customization": "Com maionese light"
        }
      ]
    },
    {
      "_id": ObjectId(),  
      "client_cpf": "55566677788",  
      "client_name": "Ana Costa",  
      "order_datetime": ISODate("2024-10-07T18:45:00Z"),  
      "status": "ready",  
      "order_number":"4",
      "items": [
        {
          "name": "Salada Caesar",
          "quantity": 1,
          "customization": "Sem croutons"
        },
        {
          "name": "Suco de Laranja",
          "quantity": 1,
          "customization": "Sem açúcar"
        }
      ]
    },
    {
      "_id": ObjectId(),  
      "client_cpf": "44455566677",  
      "client_name": "Pedro Lima",  
      "order_datetime": ISODate("2024-10-07T19:00:00Z"),
      "status": "delivered",  
      "order_number":"5",
      "items": [
        {
          "name": "Tacos",
          "quantity": 3,
          "customization": "Extra carne"
        }
      ]
    }
  ])
EOF