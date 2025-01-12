# Application Operation Guide

This guide provides instructions on how to operate the application, focusing on two roles: **User** and **Administrator**.

---

## User Instructions: From Account Creation to Order Finalization

### 1. Sign Up

Create an account by using:  
**POST** `/api/user/auth/signup`

#### Request Body Schema:

```json
{
  "email": "string",
  "password": "string",
  "cpf": "string",
  "role": "string",
  "fullName": "string"
}
```

#### Example:

```json
{
  "email": "fulano@user.com",
  "password": "1234",
  "cpf": "04953129326",
  "role": "CONSUMER",
  "fullName": "Fulano da Silva"
}
```

### 2. Log In

Log in to your account by using:
**POST** `/api/user/auth/login`

#### Request Body Schema:

```json
{
  "cpf": "string",
  "password": "string",
  "anonymous": true
}
```

#### Example:

```json
{
  "cpf": "04953129326",
  "password": "1234"
}
```
Upon successful login, a token will be generated. Save this token to use in Postman or for authorizing requests in Swagger.

### 3. View Products

Retrieve available products:

- View All Products:

  **GET** /api/products

- View Products by Category:

  **GET** /api/products/category/{category}

  Example: /api/products/category/Lanche

- View Product by ID:

  **GET** /api/products/{id}

### 4. Place an Order

Create an order using:
**POST** `/api/orders`

#### Request Body Schema (Working Example):

```json
{
  "cpf": "04953129326",
  "items": [
    {
      "name": "Pizza de Calabresa",
      "quantity": 1
    },
    {
      "name": "Coca-Cola 350mL",
      "quantity": 3
    },
    {
      "name": "Sundae de Chocolate",
      "quantity": 3
    }
  ]
}
```

### 5. Check an Order

Retrieve an order:

By Order ID:
**GET** /api/orders/{id}

By Order Number:
**GET** /api/orders/number/000001

### 6. Modify an Order

Update an existing order using:
**PUT** /api/orders

Request Body Schema (Working Example):
```json
{
  "id": "6783d012d5753c9470fe6911",
  "items": [
    {
      "name": "Hot Dog de Salsicha",
      "quantity": 1,
      "customization": "Sem molho"
    }
  ]
}
```

The id field is required and refers to the order ID.

### 7. Verify Order Update

Check if the order was updated successfully:
**GET** /api/orders/{id}

### 8. Receive Order

Update the order status from PAGO ("paid") to RECEBIDO ("received") using:
**POST** `/api/cooking/receive-order/{order_id}`

### 9. Start Order Preparation

Update the order status from RECEBIDO ("received") to PREPARACAO ("preparing") using:
**POST** `/api/cooking/start-preparation/{order_id}`

### 10. Finish Order Preparation

Update the order status from PREPARACAO ("preparing") to PRONTO ("ready") using:
**POST** `/api/cooking/finish-preparation/{order_id}`

### 11. Notify Client

Notify the client that the order is ready for pickup using:
**POST** `/api/pickup/notify/{order_id}`

### 12. Deliver the Order

Mark the order as delivered using:
**POST** `/api/pickup/delivery/{order_id}`

## Administrator Instructions: Managing Users and Products

### 1. Sign Up

Create an admin account using:
**POST** `/api/user/auth/signup`

#### Request Body Schema:

```json
{
  "email": "string",
  "password": "string",
  "cpf": "string",
  "role": "string",
  "fullName": "string"
}
```
#### Example:

```json
{
  "email": "admin@user.com",
  "password": "1234",
  "cpf": "04953129326",
  "role": "ADMIN",
  "fullName": "Admin da Silva"
}
```

### 2. Log In

Log in to the admin account using:
**POST** `/api/user/auth/login`

#### Request Body Schema:
```json
{
  "cpf": "string",
  "password": "string"
}
```

#### Example:
```json
{
  "cpf": "04953129326",
  "password": "1234"
}
```
Upon successful login, a token will be generated. Save this token to use in Postman or for authorizing requests in Swagger.

### 3. Create a Product

Add a new product using:
**POST** `/api/products`

#### Request Body Schema (Example):
```json
{
  "category": "Lanche",
  "description": "Hamburguer delicioso de São Paulo",
  "name": "Xis",
  "price": 12.0,
  "cookingTime": 5
}
```
### 4. Check a Product

Retrieve a product by its ID using:
**GET** `/api/products/{product_id}`

The _product_id_ is returned in the response when the product is created.

### 5. Modify a Product

Update a product using:
**PUT** `/api/products/{product_id}`

#### Request Body Schema (Example):
```json
{
  "category": "Lanche",
  "description": "Hamburguer delicioso de São Paulo e Rio de Janeiro",
  "name": "Xis Bolacha-Biscoito",
  "price": 15.0,
  "cookingTime": 10
}
```

### 6. Verify Product Update

Check if the product was updated successfully using:
**GET** /`api/products/{product_id}`

### 7. View Users

- Retrieve a list of users:
  **GET** `/api/user/`

- Retrieve a user by CPF:
**GET** `/api/user/cpf/{user_cpf}`

### 8. Delete a User

Remove a user by their ID using:
**DELETE** `/api/user/{user_id}`

### 9. Delete a Product

Remove a product by its ID using:
**DELETE** `/api/products/{product_id}`