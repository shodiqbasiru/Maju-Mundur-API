# Maju Mundur API - Clothes Marketplace

## Description

This is a RESTful API for a clothes marketplace application. The application allows users to register 
as customers or merchants, and create transactions.

## Technologies Used

- Java
- Spring Boot
- Maven
- PostgreSQL
- JWT
- Lombok

## Setup and Installation

1. Clone the repository: `git clone <repository-url>`
2. Navigate to the project directory: `cd <project-name>`
3. Install dependencies: `mvn install`
4. Run the application: `mvn spring-boot:run`

## Features

- User authentication
- Customer registration
- Merchant registration
- Transaction creation

## Usage

### Register a Customer

Endpoint: `<endpoint-url>`

Request body:
```json
{
  "username": "<username>",
  "password": "<password>",
  "customerName": "<customerName>"
}