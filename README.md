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

The Spring Boot application uses Spring Web for REST endpoints, Spring Data JPA and Hibernate for persistence, PostgreSQL as its database, and Flyway to version the database schema. The `demo1` packages organize the feature scaffold into controllers, services, repositories, entities, enums, and request/response DTOs. The application also exposes a health endpoint that reports application and database connectivity.

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
|   `-- main/resources/
|       |-- application.properties    Spring and database configuration
|       `-- db/migration/             Versioned schema and sample-data migrations
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

## Database schema and sample data

On startup, Flyway applies the SQL migrations in `src/main/resources/db/migration` before Hibernate validates the mapped entities. `V1` creates the tables, foreign keys, basic data checks, and indexes. `V2` inserts a small, linked demo dataset: three demo users, four products, two vending devices, restock lots, two orders, payment and dispensing history, stock reservations, sensor readings, alerts, an incident, and system configuration. Migrations run once per database and are tracked in Flyway's `flyway_schema_history` table.

The sample user password values are deliberately unusable placeholders because authentication and password hashing are not implemented yet. The seeded users are for relational/demo data only; replace their password values through a secure account setup flow before adding authentication. Existing PostgreSQL volumes keep their data and will receive the migrations on the next app startup. To recreate a clean local database and rerun all migrations, use `docker compose down -v` (this permanently removes the local database volume).

### Inspect tables and sample rows with Docker

Run these commands from the repository root in PowerShell. Start the services first if they are not already running:

```powershell
docker compose up --build -d
```

To open an interactive PostgreSQL shell inside the database container:

```powershell
docker compose exec db psql -U postgres -d freshvend_db
```

At the `freshvend_db=#` prompt, use PostgreSQL commands (these are entered inside `psql`, not directly in PowerShell):

```text
\dt
SELECT * FROM product;
SELECT * FROM users;
\q
```

To run a query directly from PowerShell without opening an interactive shell, pass it with `-c`:

```powershell
docker compose exec db psql -U postgres -d freshvend_db -c "SELECT * FROM product;"
```

List all public tables, including Flyway's migration-history table:

```powershell
docker compose exec db psql -U postgres -d freshvend_db -c "\dt"
```

To inspect every table's rows with one PowerShell command:

```powershell
docker compose exec -T db sh -c 'for t in $(psql -U postgres -d freshvend_db -Atc "SELECT tablename FROM pg_tables WHERE schemaname = current_schema() ORDER BY tablename"); do echo "===== $t ====="; psql -U postgres -d freshvend_db -c "TABLE \"$t\""; done'
```

For a fresh local database, set `spring.flyway.enabled=false` only if you are managing the schema yourself. Hibernate uses `ddl-auto=validate`, so it does not create or modify tables.

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
