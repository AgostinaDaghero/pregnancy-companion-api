# Pregnancy Companion API

REST API built with Java 21, Spring Boot and Maven.

## Run

On Windows, from the project folder:

```powershell
.\mvnw.cmd spring-boot:run
```

The application starts at `http://localhost:8080`.

## Initial endpoint

```http
GET /api/v1/status
```

Response:

```json
{
  "application": "Pregnancy Companion API",
  "status": "UP"
}
```

## Run the tests

```powershell
.\mvnw.cmd test
```
