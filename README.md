# Microservices Architecture with Spring Boot

This project consists of multiple Spring Boot microservices connected through an API Gateway and registered with a Discovery Server (Eureka). Each service is designed to handle specific functionalities, promoting modularity and ease of maintenance.

## Services Overview

1. **API Gateway**
   - **Port**: `8091`
   - Routes requests to the appropriate backend services.

2. **Authentication Service**
   - **Port**: `8088`
   - Manages user authentication and registration processes.

3. **Order Service**
   - **Port**: `8082`
   - Handles order management and performs CRUD operations for orders.

4. **Product Service**
   - **Port**: `8086`
   - Manages product information and performs CRUD operations for products.

5. **User Service**
   - **Port**: `8081`
   - Manages user data and performs CRUD operations for users.

6. **Discovery Server**
   - **Port**: `8761`
   - Facilitates service registration and discovery.

## Prerequisites

- **Docker** and **Docker Compose** must be installed on your machine.
- Ensure you have the necessary permissions to run Docker commands.

## Running the Application

1. **Build the Services:**
   Navigate to each service's directory and run:
   ```bash
   ./mvnw clean package
