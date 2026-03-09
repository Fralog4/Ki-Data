# Ki_Data Web Application (Kotlin Version)
![Dragon Ball Character](https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/df0411c5-1b85-411b-88fe-2bd41f3c83cd/dhr4h2t-ca1e82d7-e888-42db-946f-4a6a61a9abef.png?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOjdlMGQxODg5ODIyNjQzNzNhNWYwZDQxNWVhMGQyNmUwIiwiaXNzIjoidXJuOmFwcDo3ZTBkMTg4OTgyMjY0MzczYTVmMGQ0MTVlYTBkMjZlMCIsIm9iaiI6W1t7InBhdGgiOiJcL2ZcL2RmMDQxMWM1LTFiODUtNDExYi04OGZlLTJiZDQxZjNjODNjZFwvZGhyNGgydC1jYTFlODJkNy1lODg4LTQyZGItOTQ2Zi00YTZhNjFhOWFiZWYucG5nIn1dXSwiYXVkIjpbInVybjpzZXJ2aWNlOmZpbGUuZG93bmxvYWQiXX0.dOzeBbpJ6URZN53j39ad9yx3kdAYSuIDmrXbS07cY_I)
## Overview
Ki_Data is my first real web application, it's designed to manage and display information about Dragon Ball characters, like a sort of Wiki. The application provides RESTful endpoints to create, read, and delete character data. It is built using Kotlin and frameworks to ensure a robust, secure, and maintainable codebase.

## Technologies Used

### Backend
- **Kotlin**: Modern, concise, and safe programming language running on the JVM.
- **Spring Boot**: Framework for building the application, providing rapid application development features.
- **Spring Security**: Comprehensive security framework for authentication and authorization, implementing JWT-based stateless authentication.
- **Spring Data JPA**: Abstraction layer for data persistence, simplifying database interactions.
- **Hibernate**: The underlying ORM (Object-Relational Mapping) framework used by Spring Data JPA.
- **PostgreSQL**: Robust, open-source relational database management system.
- **Gradle (Kotlin DSL)**: Build automation tool using Kotlin DSL for configuration.
- **ModelMapper**: Library for object-to-object mapping, facilitating DTO to Entity conversions.
- **JWT (JSON Web Token)**: Standard for securely transmitting information between parties as a JSON object, used here for authentication.
- **Spring AI**: Framework for integrating AI capabilities into Spring applications.
- **Mistral AI**: Specific AI model integration used for the chatbot functionality.
- **SpringDoc OpenAPI**: Library to automate the generation of API documentation using OpenAPI 3 specification.

## Project Structure
The project follows a standard layered architecture:

- **controller**: REST controllers handling incoming HTTP requests and defining API endpoints.
- **dto**: Data Transfer Objects for carrying data between processes, decoupling the internal database structure from the external API.
- **model**: JPA Entities representing the database tables and their relationships.
- **repository**: Interfaces extending `JpaRepository` for data access operations.
- **service**: Business logic layer, handling the core functionality and transaction management.
- **security**: Security configuration, including JWT filters and authentication providers.
- **ai**: Components related to AI integration and chatbot services.

## Endpoints
The application exposes the following RESTful endpoints:

- `GET /kiData/characters`: Retrieve a list of all characters.
- `GET /kiData/characters/{id}`: Retrieve detailed information of a specific character by ID.
- `POST /kiData/characters`: Create a new character entry (Requires ADMIN role).
- `DELETE /kiData/characters/{id}`: Remove a character entry by ID (Requires ADMIN role).
- `GET /kiData/ai/dendechat`: Interact with the AI-powered Dende chatbot.

## Setup and Running the Application

### Prerequisites
- JDK 21
- Gradle (Wrapper included)

### Steps
1. **Clone the repository:**
   ```bash
   git clone <repository-url>
   cd Ki_Data
   ```

2. **Build the project:**
   Using the Gradle Wrapper:
   ```bash
   ./gradlew build
   ```
   (On Windows use `gradlew.bat build`)

3. **Run the application:**
   ```bash
   ./gradlew bootRun
   ```
   (On Windows use `gradlew.bat bootRun`)

## Security

The application implements stateless authentication using JWT (JSON Web Token).

### User Registration and Authentication

- **Roles**: `USER`, `ADMIN`

#### Auth Endpoints

- **Register a User**: `POST /kiData/auth/registrationUsrOnly`
   - Registers a new user with the `USER` role.
   - **Request Body**:
     ```json
     {
       "name" : "Goku",
       "last_name" : "Son", 
       "email": "goku@example.com",
       "password": "securePassword123"
     }
     ```

- **Authenticate**: `POST /kiData/auth/authentication`
   - Authenticates a user and returns a JWT token.
   - **Request Body**:
     ```json
     {
       "email": "goku@example.com",
       "password": "securePassword123"
     }
     ```
   - **Response**:
     ```json
     {
       "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
     }
     ```

### Role-based Access Control (RBAC)

Access to specific endpoints is restricted based on user roles.

- **Public Endpoints**:
    - Character retrieval (`GET`)
    - Authentication and Registration
    - AI Chat

- **Admin Only Endpoints**:
   - `POST /kiData/characters`: Create operations.
   - `DELETE /kiData/characters/{id}`: Delete operations.
