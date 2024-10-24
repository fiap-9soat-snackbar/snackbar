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