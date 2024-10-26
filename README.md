# Snackbar Application

<p align="center">
	<img alt="Spring boot" src="https://img.shields.io/badge/Spring%20Boot-6DB33F.svg?style=for-the-badge&logo=Spring-Boot&logoColor=white">
	<img alt="Maven" src="https://img.shields.io/badge/Apache%20Maven-C71A36.svg?style=for-the-badge&logo=Apache-Maven&logoColor=white">
	<img alt="MongoDb" src="https://img.shields.io/badge/MongoDB-47A248.svg?style=for-the-badge&logo=MongoDB&logoColor=white">
	<img alt="Docker" src="https://img.shields.io/badge/Docker-2496ED.svg?style=for-the-badge&logo=Docker&logoColor=white   ">
	<img alt="Ubuntu" src="https://img.shields.io/badge/Ubuntu-E95420.svg?style=for-the-badge&logo=Ubuntu&logoColor=white">
</p>

<h4 align="center"> 
  🍔 Snack bar 🍟
</h4>

<p align="center">
<a href="#about">About</a> •
<a href="#run"> Running the Application</a> •
<a href="#endpoints">API Endpoints</a> •
<a href="tecnologies">Tecnolgies<a/> •
<a href="#colab">Collaborators</a> •
<a href="#deploy">Deployed version</a> •
<a href="#license">License</a>
</p>
   
<p id="about">

## 💻 About the project
This is a backend-only application for managing products in a snackbar, following an hexagonal architecture.

The application is written in Java 21 using Spring Boot, built using Maven 3.9.9, uses MongoDB 8.0.1 as database, and runs containerized using Docker 27.2 and Docker Compose 2.29 on container images based on Ubuntu 24.04 (Noble Numbat).
</p>


   
## 🏃‍♂️‍➡️ Running the Application

<p id="run"></p>

### 👯‍♂️ Cloning the Git repository

The application repository is privately hosted on [GitHub](https://github.com/commskywalker/snackbar), with access allowed only to specific users.

Once you have access to the repository:

1. Create a new directory
2. Ensure you have Git installed on your system
3. Clone the repository using the command:

   ```
   git clone https://github.com/commskywalker/snackbar.git
   ```

### 🚢 Using Docker Compose

To run the application using Docker Compose:

1. Ensure you have Docker and Docker Compose installed on your system.
2. Build and start the application in background using the command:

   ```
   docker-compose up -d --build
   ```
   
<h6>💡OBS: If you prefer to run the application with the logs displaying in the console, run the following command above without the flag "-d"</h6>

3. The application will start and be accessible at [localhost:8080](http://localhost:8080).

To stop the application run:

```
docker-compose down -v
```

<h6>💡OBS: If you were running with logs on the console, use Ctrl+C in the terminal where docker-compose is running and then the command above. </h6> 

<p id="endpoints">
   
## 📍API Endpoints
</p>

* Before testing the APIs, ensure you have [Postman](https://www.postman.com/) or similar installed on your system.
* The MongoDB "products" collection, within the "snackbar" database, comes pre-loaded with a series of products.

The application exposes the following REST API endpoints:


| route               | description                                          
|----------------------|-----------------------------------------------------
| <kbd>GET /api/products</kbd>     | See [request details](#get-products)
| <kbd>GET /api/products/{id}</kbd>     |  See [request details](#get-products-id)
| <kbd>GET /api/products/category/{category}</kbd>     |See [request details](#get-products-category)
| <kbd>POST /api/products</kbd>     | See [request details](#post-products)
| <kbd>PUT /api/products/{id}</kbd>     | See [request details](#put-products)
| <kbd>DELETE /api/products/{id}</kbd>     | Will only return the HTTP status code. 
| <kbd>DELETE /api/products/{id}</kbd>     |  [pasta](https://github.com/commskywalker/snackbar/blob/main/database/dbLoad.sh)




<h3 id="get-products">GET /api/products</h3>

**RESPOSNSE**  
```json
{
    "success": true,
    "message": "Products retrieved successfully",
    "data": [
        {
            "id": "671bb29c52801c1c1efe6911",
            "category": "Lanche",
            "description": "Hambúrguer artesanal 160g, servido com pão de brioche, alface e tomate.",
            "name": "Hambúrguer",
            "price": 30
        },
        {
            "id": "671bb29c52801c1c1efe6912",
            "category": "Acompanhamento",
            "description": "Batatas fritas crocantes com ketchup",
            "name": "Batata frita",
            "price": 15
        }
    ]
}
```

<h3 id="get-products-id">GET /api/products/{id}</h3>

**RESPONSE**
```json
{
    "success": true,
    "message": "Product retrieved successfully",
    "data": {
        "id": "671d1ab834d76230acfe6911",
        "category": "Lanche",
        "description": "Hambúrguer artesanal 160g, servido com pão de brioche, alface e tomate.",
        "name": "Hambúrguer",
        "price": 30
    }
}
```

<h3 id="get-products-category">GET /api/products/category/{category}</h3>

**RESPONSE**
```json
{
    "success": true,
    "message": "Products retrieved successfully",
    "data": [
        {
            "id": "671d1ab834d76230acfe6911",
            "category": "Lanche",
            "description": "Hambúrguer artesanal 160g, servido com pão de brioche, alface e tomate.",
            "name": "Hambúrguer",
            "price": 30
        },
        {
            "id": "671d1ab834d76230acfe6915",
            "category": "Lanche",
            "description": "Pizza de mussarela com tomate e manjericão.",
            "name": "Pizza",
            "price": 25
        }
    ]
}
```
<h3 id="post-products">POST /registerClient/:idUser</h3>

**REQUEST**  
```json
{
    "name": "Hambúrguer",
    "category": "Lanche",
    "description": "Hambúrguer artesanal 160g, servido com pão de brioche, alface e tomate.",
    "price": 10
}
```
**RESPONSE**
```json
{
    "success": true,
    "message": "Product created successfully",
    "data": {
        "id": "671d1c91f7689b2849534586",
        "category": "Lanche",
        "description": "Hambúrguer artesanal 160g, servido com pão de brioche, alface e tomate.",
        "name": "Hambúrguer",
        "price": 10
    }
}
```

<h3 id="put-products">PUT /api/products/{id}</h3>

**REQUEST**  
```json
{
    "name": "Salada",
    "category": "Acompanhamento",
    "description": "Batatas fritas com ketchup",
    "price": 10
}
```

**RESPONSE**  
```json
{
    "success": true,
    "message": "Product updated successfully",
    "data": {
        "id": "671d1c91f7689b2849534586",
        "category": "Acompanhamento",
        "description": "Batatas fritas crocantes com ketchup",
        "name": "Batata frita",
        "price": 15
    }
}
```





Long description:

OBS: Replace `localhost:8080` with the appropriate host and port if your application is running on a different address.

`GET http://localhost:8080/api/products`: Will return a JSON list of all products.

`GET http://localhost:8080/api/products/{id}`: Expects an existing product id in a format such as 671bb29c52801c1c1efe6911 replacing {id}, and will return a JSON with the full entry of the specified product.

`GET http://localhost:8080/api/products/category/{category}`: Expects an existing product category in a format such as Lanche replacing {category}. The collection comes loaded with the categories Lanche, Acompanhamento, Bebida and Sobremesa, and will return a JSON list with all the products that match the requested category.

`POST http://localhost:8080/api/products`: Expects a json request with the body in the format of:

{
        "name": "Your_new_product_name",
        "category": "Your_product_category",
        "description": "Your product description.",
        "price": 10.0
}

and will return the JSON with the added product.

`PUT http://localhost:8080/api/products/{id}`: Expects both an existing product id in a format such as 671bb29c52801c1c1efe6911 replacing {id} in the request, as well as a json request with the body in the format of:

{
        "name": "Your_product_name",
        "category": "Your_product_category",
        "description": "Your product description.",
        "price": 10.0
}

and will return the JSON with the changed product.

You can change any of the fields, but it's important to provide all values, otherwise the values not provided will be nulled. (to be fixed)

`DELETE http://localhost:8080/api/products/{id}`: Expects an existing product id in a format such as 671bb29c52801c1c1efe6911 replacing {id}, and will only return the HTTP status code. 

### Accessing the Swagger UI

You can access the Swagger UI to explore and test the API at:

http://localhost:8080/swagger-ui.html

## Application Architecture

This application follows a hexagonal architecture, also known as ports and adapters architecture. The main components are:

- Domain: Contains the core business logic and entities (e.g., Product).
- Application: Defines the use cases and interfaces for the application (e.g., ProductService).
- Infrastructure: Implements the interfaces defined in the application layer (e.g., ProductRepository).
- Web: Handles the HTTP requests and responses (e.g., ProductController).

This architecture promotes separation of concerns and makes the application more modular and testable.

## Database
This application uses MongoDB as its database. The MongoDB database name is "snackbar", and the collection used for storing product information is "products".

## If you need to Recompile/Run the Application outside of the container

To run the application directly using Java and Maven:

1. Ensure you have Java 21 installed on your system. You can check your Java version by running:
   ```
   java -version
   ```

2. If you don't have Java 21, download and install it from the official Oracle website or use a package manager.

3. Make sure you have Maven 3.9.9 installed. You can check your Maven version by running:
   ```
   mvn -version
   ```

4. Navigate to the project's root directory (where the `pom.xml` file is located) in your terminal.

5. Build the project using Maven:
   ```
   mvn package
   ```

6. Run the application:
   ```
   java -jar target/snackbar-0.0.1-SNAPSHOT.jar
   ```
