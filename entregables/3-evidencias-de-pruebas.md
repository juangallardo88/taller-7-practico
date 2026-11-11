# Evidencias de pruebas

Ejecutado el 11/11/2026 con JDK 21 y Maven 3.9 (`mvn package` + `java -jar`). Datos iniciales de `data.sql`.

| Escenario | Petición | Esperado | Obtenido |
|---|---|---|---|
| Préstamo de libro disponible | `POST /api/prestamos {"estudianteId":1,"libroId":1}` | 201 Created | ✅ 201 |
| Libro ya prestado | `POST /api/prestamos {"estudianteId":1,"libroId":3}` | 409 Conflict | ✅ 409 |
| Estudiante inexistente | `POST /api/prestamos {"estudianteId":99,"libroId":2}` | 404 Not Found | ✅ 404 |
| Libro inexistente | `POST /api/prestamos {"estudianteId":1,"libroId":99}` | 404 Not Found | ✅ 404 |
| Consulta de préstamos | `GET /api/prestamos` | 200 OK | ✅ 200 |
| Petición inválida (sin `libroId`) | `POST /api/prestamos {"estudianteId":1}` | 400 Bad Request | ✅ 400 |

## Salida completa

```text
===== PRUEBA TALLER 7 =====
[1] Compilando con Maven (mvn package)...
COMPILACION OK
[2] Arrancando target\smartlibrary-0.0.1-SNAPSHOT.jar ...
APP ARRANCADA OK

[3] Escenarios:

--- Prestamo de libro disponible (esperado 201)
{"id":1,"fechaPrestamo":"2026-11-11","estudiante":{"id":1,"nombre":"Ana Torres","correo":"ana.torres@campusucc.edu.co"},"libro":{"id":1,"titulo":"Estructuras de Datos en Java","disponible":false}}
HTTP 201

--- Libro ya prestado (esperado 409)
El libro 3 no esta disponible
HTTP 409

--- Estudiante inexistente (esperado 404)
Estudiante no encontrado: 99
HTTP 404

--- Libro inexistente (esperado 404)
Libro no encontrado: 99
HTTP 404

--- Request invalido sin libroId (esperado 400)
{"timestamp":"2026-11-11T22:58:55.456+00:00","status":400,"error":"Bad Request","path":"/api/prestamos"}
HTTP 400

--- Consulta de prestamos (esperado 200)
[{"id":1,"fechaPrestamo":"2026-11-11","estudiante":{"id":1,"nombre":"Ana Torres","correo":"ana.torres@campusucc.edu.co"},"libro":{"id":1,"titulo":"Estructuras de Datos en Java","disponible":false}}]
HTTP 200

===== FIN =====
```
