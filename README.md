# Ki_Data Web Application

## Overview
Ki_Data is my first real web application, it's designed to manage and display information about Dragon Ball characters, like a sort of Wiki. The application provides RESTful endpoints to create, read, and delete character data. It is built using Java and frameworks to ensure a robust, secure, and maintainable codebase.

## Technologies Used

### Backend
- **Spring Boot**: Framework for building the application.
- **Spring Security**: For securing the application endpoints with role-based access control, using JWT token authentication logic.
- **Spring Data JPA**: For data persistence and interaction with the Postgres database.
- **Hibernate**: ORM framework used with Spring Data JPA.
- **Postgres Database**: Relational database for storing character data.
- **Lombok**: Library to reduce boilerplate code by generating getters, setters, and constructors.
- **ModelMapper**: Library for mapping between DTOs and entities.
- **JWT Token**: For secure token-based authentication.
- **Jakarta Persistence API (JPA)**: For ORM mapping and database interaction.

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

- `GET /kiData/characters`: Retrieve all characters.
- `GET /kiData/character/{id}`: Retrieve a character by ID.
- `POST /kiData/create`: Create a new character (Admin only).
- `DELETE /kiData/delete/{id}`: Delete a character by ID (Admin only).

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

## Security

The application uses JWT (JSON Web Token) for authentication and authorization to ensure secure access to its endpoints.

### User Registration and Authentication

- **User**: `user`, Role: `USER`/`ADMIN`

#### Endpoints

- **Register a User**: `POST /kiData/auth/register`
   - This endpoint allows new users to register by providing their details in a JSON body. The role of the user (either `USER` or `ADMIN`) must be specified.
   - **Request Body Example**:
     ```json
     {
       "id": "number",
       "name" : "name",
       "lastName" : "lastName", 
       "email": "newuser@example.com",
       "password": "newpassword",
       "role": "USER/ADMIN"
     }
     ```

- **Authenticate a User**: `POST /kiData/auth/authenticate`
   - This endpoint is used to authenticate a user. It requires the user's credentials (email and password) to be provided in a JSON body. Upon successful authentication, a JWT token is returned.
   - **Request Body Example**:
     ```json
     {
       "email": "user@example.com",
       "password": "password"
     }
     ```
   - **Response Example**:
     ```json
     {
       "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
     }
     ```

### Role-based Access Control

Role-based access control is enforced to restrict access to certain endpoints based on the user's role. Only users with the `ADMIN` role have permission to create or delete characters.

- **Endpoints accessible by ADMIN only**:
   - `POST /kiData/create`: Create a new character.
   - `DELETE /kiData/delete/{id}`: Delete a character by ID.
