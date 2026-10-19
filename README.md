# Freshvend-test

Java Spring Boot & PostgreSQL project with Docker.

---

## 🐳 Docker Containers

| Container Name | Service | Image | Host Port | Purpose |
| :--- | :--- | :--- | :--- | :--- |
| **`freshvend_app`** | `app` | Spring Boot 3 + Java 21 | `8080` | Spring Boot REST backend |
| **`freshvend_db`** | `db` | `postgres:16-alpine` | `5432` | PostgreSQL database |

---

## 🚀 Quick Start

### 1. Start Both Containers
```powershell
docker compose up --build -d
```

### 2. Check Container Status
```powershell
docker compose ps
```
You should see:
```text
NAME              IMAGE                    STATUS                   PORTS
freshvend_app     freshvend-test-app       Up                       0.0.0.0:8080->8080/tcp
freshvend_db      postgres:16-alpine       Up (healthy)             0.0.0.0:5432->5432/tcp
```

### 3. Check Logs
```powershell
# Follow all logs
docker compose logs -f

# Follow Spring Boot container logs
docker logs -f freshvend_app

# Follow PostgreSQL container logs
docker logs -f freshvend_db
```

### 4. Verify Application and Database Health
Once running, check the health endpoint:
- **Browser**: [http://localhost:8080/api/health](http://localhost:8080/api/health)
- **PowerShell / Terminal**:
  ```powershell
  curl.exe http://localhost:8080/api/health
  ```

*Expected JSON output:*
```json
{
  "service": "Freshvend-test",
  "status": "UP",
  "database": {
    "status": "UP",
    "databaseProduct": "PostgreSQL",
    "databaseVersion": "16.x"
  }
}
```

### 5. Access PostgreSQL CLI Directly
```powershell
docker exec -it freshvend_db psql -U postgres -d freshvend_db
```

### 6. Stop Containers
```powershell
docker compose down

# To also remove persistent database volume:
docker compose down -v
```

---

## ⚙️ Database Connection Parameters

- **Database Name**: `freshvend_db`
- **Username**: `postgres`
- **Password**: `postgres`
- **Host (from local machine / DBeaver)**: `localhost:5432`
- **Host (inside Docker network)**: `freshvend_db:5432`

---

## 📁 Project Structure

```
Freshvend-test/
├── src/
│   ├── main/
│   │   ├── java/com/freshvend/
│   │   │   ├── FreshvendApplication.java
│   │   │   └── controller/
│   │   │       └── HealthController.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/com/freshvend/
│           └── FreshvendApplicationTests.java
├── docker-compose.yml
├── Dockerfile
├── pom.xml
├── .gitignore
├── .dockerignore
└── README.md
```
