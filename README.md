# Pregnancy Companion API

API REST creada con Java 21, Spring Boot y Maven.

## Ejecutar

En Windows, desde la carpeta del proyecto:

```powershell
.\mvnw.cmd spring-boot:run
```

La aplicación se inicia en `http://localhost:8080`.

## Endpoint inicial

```http
GET /api/v1/status
```

Respuesta:

```json
{
  "application": "Pregnancy Companion API",
  "status": "UP"
}
```

## Ejecutar las pruebas

```powershell
.\mvnw.cmd test
```
