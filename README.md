# MedDrone - Drone Delivery System
This is a Spring Boot application for managing a fleet of drones that deliver medications. The application provides RESTful APIs to register drones, load them with medications, check their battery levels, and more.

## Project Setup

### Prerequisites

- Java 11 or higher
- Maven 3.6.3 or higher
- IntelliJ IDEA (or any preferred Java IDE/editor)

### Project Structure

The project uses the following dependencies:
- **Spring Boot Starter Web**: For building web applications.
- **Spring Boot Starter Data JPA**: For data persistence with JPA and Hibernate.
- **H2 Database**: In-memory database for development purposes.
- **Spring Boot DevTools**: For development-time features.
- **Lombok**: For reducing boilerplate code.
- **Spring REST Docs**: For documenting RESTful APIs.

### Project Metadata

- **Group**: `DroneDeliveryService`
- **Artifact**: `Ajua`
- **Name**: `Ajua`
- **Description**: `Drone delivery system`
- **Package Name**: `DroneDeliveryService.Ajua`
- **Packaging**: `Jar`
- **Java Version**: `11`
- **Spring Boot Version**: `3.3.1`

### Dependencies

- Lombok
- Spring Data JPA
- H2 Database
- Spring Boot DevTools
- Spring REST Docs

## Getting Started

### Configuration

#### Database Configuration

The project uses H2 as an in-memory database for development. No additional setup is required.

#### IDE Setup

1. **Open the Project**: Import the project into IntelliJ IDEA.
2. **Resolve Dependencies**: Dependencies will automatically be downloaded by Maven.

### Running the Application

To run the application locally:
- Run the main application class `AjuaApplication` in IntelliJ IDEA.
- The application will start on port 8080 by default.

### API Documentation

Once the application is running, you can access the API documentation using Swagger UI or other API documentation tools.

### Testing

The project includes unit tests and can be expanded with integration tests using tools like JUnit.

### Additional Notes

- For production deployment, configure a h2 database or other relational database.
- Modify `application.properties` for database configuration as needed.

This README.md provides a basic overview to get started with the Drone Delivery System. For detailed API usage and further customization, refer to the source code and documentation within the project.
