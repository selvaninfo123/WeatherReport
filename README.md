Here is a `README.md` file for your project:

```markdown
# Weather Application

This is a Spring Boot application that integrates with a weather report Open API to fetch and store weather data. The application also provides endpoints to retrieve weather history.

## Features

- Fetch weather data from an external API
- Store weather data in a database
- Retrieve weather history by user or postal code
- Dockerized for easy deployment

## Technologies Used

- Java
- Spring Boot
- Maven
- Docker
- H2 Database (for testing purposes)

## Prerequisites

- Java 17
- Maven
- Docker

## Getting Started

### Clone the repository

```sh
git clone https://github.com/selvanvalavan/weather-app.git
cd weather-app
```

### Build the project

```sh
mvn clean package
```

### Run the application

```sh
mvn spring-boot:run
```

The application will be available at `http://localhost:8080`.

## API Endpoints

### Fetch Weather Data

- **URL:** `/api/weather`
- **Method:** `POST`
- **Content-Type:** `application/json`
- **Request Body:**
  ```json
  {
    "user": 1,
    "postalCode": "12345"
  }
  ```
- **Response:**
  ```json
  {
    "id": 1,
    "userName": "testUser",
    "postalCode": "12345",
    "temperature": 25.0,
    "humidity": 60.0,
    "weatherConditions": "Sunny"
  }
  ```

### Get Weather History

- **URL:** `/api/weather/history`
- **Method:** `GET`
- **Query Parameters:**
  - `user` (optional)
  - `postalCode` (optional)
- **Response:**
  ```json
  [
    {
      "id": 1,
      "userName": "testUser",
      "postalCode": "12345",
      "temperature": 25.0,
      "humidity": 60.0,
      "weatherConditions": "Sunny"
    }
  ]
  ```

## Running Tests

To run the tests, use the following command:

```sh
mvn test
```

## Docker

### Build Docker Image

```sh
docker build -t weather-app .
```

### Run Docker Container

```sh
docker run -p 8080:8080 weather-app
```

### Docker Compose

To use Docker Compose, create a `docker-compose.yml` file and run:

```sh
docker-compose up --build
```

