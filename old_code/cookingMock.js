const database = 'restaurant';
const collection = 'orders';

use(database);

db.createCollection(collection);

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
  ]);
