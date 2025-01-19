// Conectar ao MongoDB
db = db.getSiblingDB('snackbar');

// Inserir registros na collection 'orders'
db.orders.insertMany([
  {
    _id: "678d6a63ec453308f7087d0c",
    orderNumber: "000007",
    orderDateTime:  "1737321055250",
    cpf: "00000000001",
    name: "Jose Moreira",
    items: [
      {
        name: "Hambúrguer",
        quantity:  "2",
        price: "22.00",
        cookingTime: "10",
        customization: ""
      }
    ],
    statusOrder: "NOVO",
    totalPrice: "44.00",
    remainingTime: "0",
  },
  {
    _id: "678d6a63ec453308f7087d0d",
    orderNumber: "000008",
    orderDateTime: "1737321055251",
    cpf: "00000000002",
    name: "Rosangela Santos",
    items: [
      {
        name: "Hambúrguer",
        quantity: "1",
        price: "22.00",
        cookingTime: "10",
        customization: ""
      },
      {
        name: "Coca-Cola 350ml",
        quantity: "1",
        price: "8.00",
        cookingTime: "1",
        customization: ""
      }
    ],
    statusOrder: "NOVO",
    totalPrice: "30.00",
    remainingTime: "0",
  }
]);

print("Collection 'products' restored.");
