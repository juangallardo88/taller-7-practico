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

Hay que tener instalado Java y Maven. Se abre una terminal en la carpeta del proyecto y se escribe:

```
mvn spring-boot:run
```

Cuando arranca, el programa ya tiene guardados unos estudiantes y unos libros de ejemplo para poder probar.

## Cómo probarlo

Se puede probar con Postman:

1. Para pedir un libro prestado se manda un POST a `http://localhost:8080/api/prestamos` con el número del estudiante y del libro, por ejemplo `{"estudianteId": 1, "libroId": 1}`.
2. Para ver todos los préstamos se hace un GET a `http://localhost:8080/api/prestamos`.

Si el libro se puede prestar, el préstamo se guarda. Si el libro ya está prestado o el estudiante o el libro no existen, el programa avisa y no guarda nada.
