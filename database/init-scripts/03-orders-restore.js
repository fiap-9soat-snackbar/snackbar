// Conectar ao MongoDB
db = db.getSiblingDB('snackbar');

// Inserir registros na collection 'orders'
db.orders.insertMany([
    {
      "id": "67230df5305e6e531c4149a2",
      "orderNumber": "000001",
      "orderDateTime": "2024-10-31T04:52:17.367Z",
      "cpf": "12345",
      "name": "Jose Moreira",
      "items": [
        {
          "name": "Hot Dog de Salsicha",
          "price": 20.00,
          "quantity": 1,
          "cookingTime": 10,
          "customization": "Molho extra"
        }
      ],
      "statusOrder": "NOVO",
      "waitingTime": 10,
      "totalPrice": 20.00
    },
    {
      "id": "67230e25305e6e531c4149a3",
      "orderNumber": "000002",
      "orderDateTime": "2024-10-31T04:52:17.367Z",
      "cpf": "12346",
      "name": "Rosangela Santos",
      "items": [
        {
          "name": "Hambúrguer",
          "price": 22,
          "quantity": 1,
          "cookingTime": 10,
        },
        {
          "name": "Coca-Cola 350ml",
          "price": 8,
          "quantity": 1,
          "cookingTime": 1,
        }
      ],
      "statusOrder": "NOVO",
      "waitingTime": 11,
      "totalPrice": 30.00
    }
]);

print("Collection 'products' restored.");

