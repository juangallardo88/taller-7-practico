# Taller práctico 7 - SmartLibrary

Proyecto del taller del bloque 7 de Diseño de Software. Es una API hecha en Spring Boot para registrar préstamos de libros a estudiantes. El código está organizado en tres capas: controller, service y repository.

## Qué hay en el repositorio

- `diagramas/Taller Bloque 7 SmartLibrary.vpp`: los diagramas en Visual Paradigm
- `diagramas/`: las imágenes de los diagramas (modelo inicial, clases por capas y secuencia)
- `entregables/1-tabla-de-responsabilidades.md`
- `entregables/2-decisiones-de-diseno.md`
- `entregables/3-evidencias-de-pruebas.md`
- `src/`: el código del proyecto

## Diagramas

Modelo inicial:

![Modelo inicial](diagramas/1-vista-logica-modelo-inicial.png)

Clases por capas:

![Clases por capas](diagramas/2-clases-por-capas.png)

Secuencia de registrar préstamo:

![Secuencia](diagramas/3-secuencia-registrar-prestamo.png)

## Cómo ejecutarlo

Se necesita Java 21 y Maven.

```
mvn spring-boot:run
```

La base de datos es H2 y ya trae algunos estudiantes y libros de prueba (el libro 3 ya está prestado).

## Cómo probarlo

Registrar un préstamo:

```
POST http://localhost:8080/api/prestamos
{"estudianteId": 1, "libroId": 1}
```

Ver los préstamos:

```
GET http://localhost:8080/api/prestamos
```

Si el libro está disponible responde 201, si ya está prestado responde 409 y si el estudiante o el libro no existen responde 404.
