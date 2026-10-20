# Freshvend

Freshvend is a Spring Boot REST API for a fresh food vending system. The project contains a Java 21 application, a PostgreSQL database, a Docker Compose setup, and editable Mermaid design diagrams.

## System overview

Docker Compose starts two services:

```text
Client
  |
  | HTTP, port 8080
  v
Spring Boot application
  |
  | PostgreSQL, port 5432
  v
PostgreSQL database
```

The Spring Boot application uses Spring Web for REST endpoints, Spring Data JPA and Hibernate for persistence, and PostgreSQL as its database. The `demo1` packages organize the feature scaffold into controllers, services, repositories, entities, enums, and request/response DTOs. The application also exposes a health endpoint that reports application and database connectivity.

## Repository structure

```text
Freshvend-test/
|-- diagrams/                         Mermaid sources and PNG previews
|-- docs/                             Project documentation
|-- src/
|   |-- main/java/com/freshvend/
|   |   |-- controller/               Application health endpoint
|   |   `-- demo1/
|   |       |-- controller/           REST controllers
|   |       |-- domain/entity/         JPA entities
|   |       |-- domain/enums/          Domain status and type enums
|   |       |-- dto/request/           API request models
|   |       |-- dto/response/          API response models
|   |       |-- repository/            Spring Data repositories
|   |       `-- service/               Application services
|   `-- main/resources/                Spring configuration
|-- docker-compose.yml                 Application and PostgreSQL services
|-- Dockerfile                         Multi-stage Java 21 container build
`-- pom.xml                            Maven project and dependencies
```

The design diagram index is [diagrams/README.md](diagrams/README.md). Each diagram has an editable `.mmd` source and a `.png` preview.

## Run with Docker Compose

Requirements: Docker Engine with the Docker Compose plugin.

From the repository root, build and start the application and database:

```powershell
docker compose up --build -d
```

Check service status and follow application logs:

```powershell
docker compose ps
docker compose logs -f app
```

The API listens on `http://localhost:8080`. PostgreSQL is published on port `5432`. Check application and database connectivity at:

```text
http://localhost:8080/api/health
```

Stop the services while retaining database data:

```powershell
docker compose down
```

Remove the services and the database volume, including its stored data:

```powershell
docker compose down -v
```

The Compose configuration creates a named volume called `freshvend_pgdata`, so database records remain after a normal stop and restart. The Compose file currently uses development credentials (`postgres` / `postgres`); replace them before using this setup outside local development.

## Run locally

Requirements: Java 21, Maven, and a PostgreSQL database. Start PostgreSQL separately, then run the application from the repository root:

```powershell
mvn spring-boot:run
```

By default, the application connects to `localhost:5432`, database `freshvend_db`, with username and password `postgres`. Configure the connection with these environment variables:

| Variable | Default | Description |
| --- | --- | --- |
| `DB_HOST` | `localhost` | PostgreSQL host |
| `DB_PORT` | `5432` | PostgreSQL port |
| `DB_NAME` | `freshvend_db` | Database name |
| `DB_USERNAME` | `postgres` | Database username |
| `DB_PASSWORD` | `postgres` | Database password |

For example, in PowerShell:

```powershell
$env:DB_HOST = "localhost"
$env:DB_PORT = "5432"
$env:DB_NAME = "freshvend_db"
$env:DB_USERNAME = "postgres"
$env:DB_PASSWORD = "postgres"
mvn spring-boot:run
```

## Health endpoint

`GET /api/health` returns the application status and PostgreSQL connection status, including the database product and version when the connection succeeds.
