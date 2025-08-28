# Dating App - Backend

This directory contains the backend services for the Dating App project. It follows a modular, multi-project Gradle setup.

## Architecture

The backend is built using Java 21, Spring Boot 3, and follows principles of Hexagonal Architecture. It is divided into several modules, each representing a bounded context:

- `application`: The main Spring Boot application, responsible for wiring everything together and running the web server.
- `auth`: Handles user authentication, registration, and security concerns.
- `profiles`: Manages user profiles, preferences, and related data.
- `matching`: Contains the logic for matching users, including the recommendation algorithm.
- `chat`: Powers the real-time chat functionality between matched users.

## Local Development Environment

We use Docker Compose to manage the necessary infrastructure services for local development.

### Prerequisites

- Java 21
- Docker and Docker Compose

### Running the Services

To start all the required services (PostgreSQL, Redis, Kafka, MinIO), run the following command from the **root directory** of the project:

```bash
docker-compose up -d
```

This will launch all services in detached mode. You can view logs using `docker-compose logs -f`.

To stop the services, run:

```bash
docker-compose down
```

## Building the Backend

To build all the backend modules, you can use the Gradle wrapper from the root of the project:

```bash
./gradlew :backend:application:build
```

This command will compile the code, run tests, and assemble the final JAR for the main application.
