# Planet-a-thon Backend

This project is a sample Spring Boot 3 application written in Kotlin that exposes a REST API for performing CRUD operations on Cartoon objects.

## Project Structure
The project contains the following folder structure:
- **controller**: Contains the REST API endpoints for the Cartoon resource.
- **dto**: Contains the DTO classes (Data Transfer Objects) that are used to transfer data between the controller and service layers of the application.
- **exception**: Contains custom exception classes for handling errors in the application.
- **model**: Contains the data models (entities) used for persisting data to the database.
- **repository**: Contains the repository interfaces for the entities used in the application.
- **security**: Contains the security configuration for the application.
- **service**: Contains the service layer for the Cartoon resource.
- **PatBackendApplication.kt**: The main entry point for the application.

## Functionality
The Cartoon REST API supports CRUD (Create, Read, Update, Delete) operations on Cartoon objects. The API exposes the following endpoints:

- GET `/api/cartoons`: Retrieves a paginated list of all cartoons in the database.
- GET `/api/cartoons/{id}`: Retrieves a single cartoon with the provided ID.
- POST `/api/cartoons`: Creates a new cartoon in the database with the provided data.
- PATCH `/api/cartoons`: Updates an existing Cartoon object with a subset of its original properties.
- DELETE `/api/cartoons/{id}`: Deletes the cartoon with the provided ID from the database.

The API also includes basic security features such as API key authentication for requests, security configuration, and custom exception handling to provide helpful error messages for clients.

## Getting Started

To get started with the project, follow these steps:

1. Clone the repository to your local machine.
2. Open the project in your favourite IDE.
3. Build the project to download the required dependencies.
4. Run the `PatBackendApplication.kt` class to start the Spring Boot application.

Once the application is running, you can test the API endpoints using a tool such as Postman or cURL.