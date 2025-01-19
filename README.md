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

The application is written in Java 21 using Spring Boot, built using Maven 3.9.9, uses MongoDB 8.0.1 as database, and runs containerized using Docker 27.2 and Docker Compose 2.29 on container images based on Ubuntu 24.04 (Noble Numbat) of amd64 architecture.

The Domain Drive Design (DDD) diagrams that define the main application flows are accessible in this Miro board: https://miro.com/app/board/uXjVLK2yXLA=/

The two videos that describe the user flow and the admin flow are hosted in Youtube in the following links (only visible through the links, not searcheable):

Project presentation and user flow: part 01 - https://youtu.be/T2oW0KYMC-U and part 02 - https://youtu.be/y6bCACwyYLU 
Admin flow: - https://youtu.be/14HJadw8JQ0 

</p>
   

<p id="run">

## 🏃‍♂️‍➡️ Running the Application
</p>

The application repository is privately hosted on [GitHub](https://github.com/commskywalker/snackbar), with access allowed only to specific users.

### To run the application: 
* Ensure you are running a system with Windows Subsystem for Linux (WSL) with Ubuntu 22 or 24 installed, Ubuntu 22 or 24 directly or MacOS Sequoia (version 15)
* Ensure you have Git installed on your system
* Ensure you have Docker and Docker Compose installed on your system.

```bash
# Create a new directory
$ mkdir grupo-82
# Access the new directory
$ cd grupo-82
# Clone this repository:
$ git clone https://github.com/commskywalker/snackbar.git
# Access the cloned repository directory
$ cd snackbar
# Build and start the application in background using the command:
$ docker-compose up -d --build

```
The application will start and be accessible at [localhost:8080](http://localhost:8080).

#### Other Docker Compose instructions:
```bash
# To build and start the application in foreground with the logs displaying in the console, use the following command:
$ docker-compose up --build

# Stop the application using the command:
$ docker-compose down -v
# If you were running in foregroud with logs displaying, use Ctrl+C in the terminal where docker-compose is running and then the command above

```

<p id="endpoints">
   
## 📍API Endpoints
</p>

* Before testing the APIs, ensure you have [Postman](https://www.postman.com/) or similar installed on your system.
* The MongoDB "snackbar" database comes with two collections pre-loaded: "products" and "orders".

The application exposes the following REST API endpoints:

<div align="center">

| route               | description                                          
|----------------------|-----------------------------------------------------
| <kbd>Identity and Access Management (IAM) Endpoints</kbd>     | See [iam.md](https://github.com/commskywalker/snackbar/blob/main/backend/src/main/java/com/snackbar/iam/iam.md)
| <kbd>Products Endpoints</kbd>     | See [products.md](https://github.com/commskywalker/snackbar/blob/main/backend/src/main/java/com/snackbar/product/products.md)
| <kbd>Orders Endpoints</kbd>     | See [orders.md](https://github.com/commskywalker/snackbar/blob/main/backend/src/main/java/com/snackbar/order/orders.md)
| <kbd>Cooking Endpoints</kbd>     | See [cooking.md](https://github.com/commskywalker/snackbar/blob/main/backend/src/main/java/com/snackbar/cooking/cooking.md)
| <kbd>Checkout Endpoints</kbd>     | See [checkout.md](https://github.com/commskywalker/snackbar/blob/main/backend/src/main/java/com/snackbar/checkout/checkout.md)
| <kbd>Pickup Endpoints</kbd>     | See [pickup.md](https://github.com/commskywalker/snackbar/blob/main/backend/src/main/java/com/snackbar/pickup/pickup.md)
</div>


<p id="swagger">
	
## 📄 Accessing the Swagger UI
</p>

You can access the Swagger UI to explore and test the APIs at:

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
This application uses MongoDB as its database. The MongoDB database name is "snackbar". It requires authentication and have pre-loaded an administrative user and a regular user, this last one used by the Java application with read/write access only to the "snackbar" database. 

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

3. Navigate in your terminal to the project's root directory (where the `docker-compose.yml` file is located).
```bash
# Build the project using Maven:
$ mvn -f ./backend/pom.xml package

# Run the application:
$ java -jar ./backend/target/snackbar-0.0.1-SNAPSHOT.jar

```

# Kubernetes Provisioning

This project consists of an application composed of a MongoDB and a Java Spring Boot application. Both components can be provisioned in Kubernetes in two ways: using **EKS** (Elastic Kubernetes Service) from AWS or **Minikube**. This README provides detailed instructions for environment provisioning, either locally or on AWS.

## Prerequisites

Before you begin, ensure that the following tools are installed on your environment:

- [AWS CLI](https://docs.aws.amazon.com/cli/latest/userguide/install-cliv2.html)
- [Helm](https://helm.sh/docs/intro/install/)
- [Terraform](https://learn.hashicorp.com/tutorials/terraform/install-cli)
- [Minikube](https://minikube.sigs.k8s.io/docs/start/)

If any of these tools are not installed, follow the links above to complete the installation.

# Kubernetes Specifications

## 1. Technologies Used:
- **Backend API:** Java Spring Boot (Java 21, using Maven 3.9.9).
- **Database:** MongoDB 8.0.1 in replicaset mode.
- **Container Orchestration:** Minikube v1.34.0 / AWS EKS v1.31.
- **Package Management:** Helm v3.15.3.
- **IaC:** Terraform v1.10.3.

## 2. System Components

### Backend (Java Spring Boot):
- Configured as a **Deployment** and implemented via Helm Chart.
   - Main container **snackbar** used for the API service.
- Communication via port **8080** for APIs exposed by the Kubernetes Service.
- Secret configuration stored in **Kubernetes Secret**:
  - **snackbar Secret:** Stores the database access credentials, connection string, JWT token, and its validity period.
- **Horizontal Pod Autoscaling (HPA)** enabled for scaling based on CPU and memory usage.
- Lineness and Readiness probes configured for health checks.

### MongoDB:
- Configured as a **StatefulSet** and implemented via Helm Chart.
   - Init container **wait-mongo** used to configure the MongoDB replicaset.
   - Main container **mongo-c** used as the primary container for MongoDB database.
- StatefulSet starts with **2 replicas** needed for replicaset mode.
- Communication via port **27017** exposed by the Kubernetes Service.
- Secret configuration stored in **Kubernetes Secret**:
  - **mongodb Secret:** Stores the users and passwords created during the database startup.

## Infrastructure Provisioning

The infrastructure can be provisioned with the `start-kubernetes.sh` script, which offers two environment options:

### Starting the Provisioning

1. Navigate to the project root.
2. Run the following command to start the provisioning:

   ```bash
   ./start-kubernetes.sh

   ```
3. The script will prompt you to choose between two environment options:

   1. Provision with Minikube (local)
   2. Provision with EKS (AWS)
Choose the desired option by entering **1** or **2**.

### Provisioning with Minikube

If you choose option **1**, the environment will be configured locally with Minikube. The script `infra/scripts/start-minikube.sh` will be executed to provision the application and MongoDB in the local cluster.

### Provisioning with EKS

If you choose option **2**, the environment will be configured on AWS using EKS. The script `infra/scripts/start-eks.sh` will be responsible for provisioning the resources on AWS and configuring the Kubernetes cluster on EKS.

### What do these scripts do?

- Start a Kubernetes Cluster v1.31 on Minikube or AWS EKS.
- Create the ns-snackbar namespace.
- Install the Helm chart for the Snackbar application.
- Install the MongoDB database used by the Snackbar application in Replicaset configuration.
- Connect the Snackbar and MongoDB services to the user's machine via port-forward (Minikube).
- Provision a LoadBalancer so that the user can access the Snackbar and MongoDB services (AWS EKS).
- List the pods that were created.

### Destroying the Infrastructure

If you want to remove the provisioned environment, use the `destroy-kubernetes.sh` script. It works similarly to the creation script and allows for the removal of the Kubernetes cluster based on your choice (Minikube or EKS).

To start the destruction process, run:

   ```bash
   ./destroy-kubernetes.sh

   ```
### Infrastructure Architecture

Available at: [miro.com.br](https://miro.com/app/board/uXjVL0azFlU=/?track=true&utm_source=notification&utm_medium=email&utm_campaign=approve-request&utm_content=go-to-miro&lid=1v8fyk3ru6qu)