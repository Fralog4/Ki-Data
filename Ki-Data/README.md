# Ki_Data Web Application

## Overview
Ki_Data is a web application designed to manage and display information about characters, specifically focusing on characters from the Dragon Ball series. The application provides RESTful endpoints to create, read, and delete character data. It is built using Java and several modern technologies and frameworks to ensure a robust, secure, and maintainable codebase.

## Technologies Used

### Backend
- **Spring Boot**: Framework for building the application.
- **Spring Security**: For securing the application endpoints with role-based access control.
- **Spring Data JPA**: For data persistence and interaction with the H2 database.
- **Hibernate**: ORM framework used with Spring Data JPA.
- **H2 Database**: In-memory database used for development and testing.
- **Lombok**: Library to reduce boilerplate code by generating getters, setters, and constructors.
- **ModelMapper**: Library for mapping between DTOs and entities.

### Other
- **Jakarta Persistence API (JPA)**: For ORM mapping and database interaction.
- **Java 11**: The programming language used for development.

## Project Structure
The project is organized into several packages, each serving a distinct purpose:

- **controller**: Contains REST controllers to handle HTTP requests.
- **dto**: Data Transfer Objects used for transferring data between layers.
- **model**: Entity classes representing the database schema.
- **repository**: Spring Data JPA repositories for data access.
- **service**: Service layer containing business logic.
- **security**: Configuration classes for application security.

## Endpoints
The application exposes the following endpoints:

- `GET /characters`: Retrieve all characters.
- `GET /characters/{id}`: Retrieve a character by ID.
- `POST /characters/create`: Create a new character (Admin only).
- `DELETE /characters/delete/{id}`: Delete a character by ID (Admin only).

## Setup and Running the Application

### Prerequisites
- Java 11 or higher
- Maven

### Steps
1. **Clone the repository:**
   ```bash
   git clone <repository-url>
   cd Ki_Data

2. ***Build the project:*** 
   ```bash
   mvn clean install
3. **Run the application:**
   ```bash
   mvn spring-boot:run

4. ***Access the H2 Console:***
   ```bash
   Access the H2 Console:
   The H2 database console can be accessed at http://localhost:8080/h2-console. 
   Use the following settings to log in:

   JDBC URL: jdbc:h2:file:./data/mydatabase
   User Name: sa
   Password: (leave empty)

# Security
The application uses Spring Security for authentication and authorization. Two users are pre-configured for testing purposes:

***User: user, Password: user, Role: USER***

***Admin: admin, Password: admin, Role: ADMIN***

Role-based access control is enforced for certain endpoints:

POST /characters/create and DELETE /characters/delete/{id} endpoints are restricted to users with the **ADMIN** role.


Feel free to customize and adjust this README to better fit your specific project details and preferences.
