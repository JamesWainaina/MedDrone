# Ajua - Drone Delivery System

Ajua is a Spring Boot application for managing a fleet of drones that deliver medications. The application provides RESTful APIs to register drones, load them with medications, check their battery levels, and more.

## Project Setup

### Prerequisites

- Java 11 or higher
- Maven 3.6.3 or higher
- MySQL Server

### Project Structure

The project uses the following dependencies:
- **Spring Boot Starter Web**: For building web applications.
- **Spring Boot Starter Data JPA**: For data persistence with JPA and Hibernate.
- **MySQL Driver**: For MySQL database connectivity.
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
- MySQL Driver
- Spring Boot DevTools
- Spring REST Docs

## Getting Started

### Configuration

#### Database Configuration

Ensure you have MySQL installed and running. Create a database named `meddrone`:

```sql
CREATE DATABASE meddrone;

