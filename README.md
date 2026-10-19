# Freshvend-test

Spring Boot REST API with PostgreSQL and Docker Compose. The repository includes a runnable application foundation, the `demo1` team scaffold, requirements documents, and design diagrams.

## Project map

```text
Freshvend-test/
├── diagrams/                      # Mermaid sources and rendered PNGs
├── docs/                           # Requirements, proposal, and scaffold guide
├── src/main/java/com/freshvend/    # Spring Boot application
│   ├── controller/                 # Health endpoint
│   └── demo1/                      # Domain, DTO, repository, service, controller scaffold
├── src/main/resources/             # Application configuration
├── src/test/                       # Application tests
├── docker-compose.yml              # App and PostgreSQL services
├── Dockerfile
└── pom.xml                         # Maven dependencies and build
```

Use [docs/README.md](docs/README.md) to browse requirements and project documents, [docs/demo1-scaffold.md](docs/demo1-scaffold.md) for the Java package map and suggested team split, and [diagrams/README.md](diagrams/README.md) to browse the diagrams.

## Run with Docker

Start the application and database:

```powershell
docker compose up --build -d
docker compose ps
```

The application listens on port `8080`; PostgreSQL listens on port `5432`. Check the health endpoint at [http://localhost:8080/api/health](http://localhost:8080/api/health), or run:

```powershell
curl.exe http://localhost:8080/api/health
```

View logs and stop the services:

```powershell
docker compose logs -f
docker compose down
```

`docker compose down -v` also removes the local database volume and its data.

## Build locally

Requires Java 21 and Maven:

```powershell
mvn spring-boot:run
mvn test
```

Configure database connection settings through `src/main/resources/application.properties` or environment variables as defined there.
