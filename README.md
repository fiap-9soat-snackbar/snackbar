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
<a href="#endpoints">Endpoints</a> •
<a href="#swagger">Swagger</a> •
<a href="#architecture">Application Architecture</a> •
<a href="#database">Database</a> •
<a href="#run-outside">Run application outside of the container</a> 
</p>
   
<p id="about">

## 💻 About the project
This is a backend-only application for managing products in a snackbar, following an hexagonal architecture.

The application is written in Java 21 using Spring Boot, built using Maven 3.9.9, uses MongoDB 8.0.1 as database, and runs containerized using Docker 27.2 and Docker Compose 2.29 on container images based on Ubuntu 24.04 (Noble Numbat).
</p>


   

<p id="run">

## 🏃‍♂️‍➡️ Running the Application
</p>

The application repository is privately hosted on [GitHub](https://github.com/commskywalker/snackbar), with access allowed only to specific users.

### To run the application: 
* Ensure you have Git installed on your system
* Ensure you have Docker and Docker Compose installed on your system.

```bash
# Create a new directory

# Clone this repository:
$ git clone https://github.com/commskywalker/snackbar.git

```
The application will start and be accessible at [localhost:8080](http://localhost:8080).

#### Using Docker Compose:
```bash
# Build and start the application in background with the logs displaying in the console using the command:
$ docker-compose up -d --build

# To build and start the application without the logs displaying run:
$ docker-compose up --build

# Stop the application using the command:
$ docker-compose down -v
# If you were running with logs, use Ctrl+C in the terminal where docker-compose is running and then the command above

```


<p id="endpoints">
   
## 📍API Endpoints
</p>

* Before testing the APIs, ensure you have [Postman](https://www.postman.com/) or similar installed on your system.
* The MongoDB "products" collection, within the "snackbar" database, comes pre-loaded with a series of products.

The application exposes the following REST API endpoints:

<div align="center">

| route               | description                                          
|----------------------|-----------------------------------------------------
| <kbd>Products Endpoints</kbd>     | See [products.md](https://github.com/commskywalker/snackbar/blob/main/backend/products.md)
</div>


<p id="swagger">
	
## 📄 Accessing the Swagger UI
</p>

You can access the Swagger UI to explore and test the API at:

http://localhost:8080/swagger-ui.html

<p id="architecture">
	
## 🏛️ Application Architecture
</p>

This application follows a hexagonal architecture, also known as ports and adapters architecture. The main components are:

- Domain: Contains the core business logic and entities (e.g., Product).
- Application: Defines the use cases and interfaces for the application (e.g., ProductService).
- Infrastructure: Implements the interfaces defined in the application layer (e.g., ProductRepository).
- Web: Handles the HTTP requests and responses (e.g., ProductController).

This architecture promotes separation of concerns and makes the application more modular and testable.

<p id="database">
	
## 🗃️ Database
</p>
This application uses MongoDB as its database. The MongoDB database name is "snackbar", and the collection used for storing product information is "products".

<p id="run-outside">
	
## ❕If you need to Recompile/Run the Application outside of the container
</p>


### To run the application directly using Java and Maven:

1. Ensure you have Java 21 installed on your system. You can check your Java version by running:
<h6>💡If you don't have Java 21, download and install it from the official Oracle website or use a package manager. </h6>

   ```
   java -version
   ```


2. Make sure you have Maven 3.9.9 installed. You can check your Maven version by running:
   ```
   mvn -version
   ```

3. Navigate to the project's root directory (where the `pom.xml` file is located) in your terminal.

4. Build the project using Maven:
   ```
   mvn package
   ```

6. Run the application:
   ```
   java -jar target/snackbar-0.0.1-SNAPSHOT.jar
   ```
