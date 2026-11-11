# Decisiones de diseño

**1. Del modelo inicial al modelo por capas.** En el modelo inicial, `GestorPrestamos` concentraba todo: guardaba estudiantes, libros y préstamos en listas en memoria y además ejecutaba la regla de negocio. Eso le daba baja cohesión y alto acoplamiento. Esas responsabilidades se repartieron en tres capas: `PrestamoController` (presentación HTTP), `PrestamoService` (coordinación del caso de uso) y `EstudianteRepository`, `LibroRepository` y `PrestamoRepository` (persistencia). Las entidades `Estudiante`, `Libro` y `Prestamo` son el modelo de dominio que usan las capas; no son una cuarta capa.

**2. Dirección de dependencias.** Se respeta Controller → Service → Repository. El controller nunca llama a un repositorio, así que la lógica de negocio no queda mezclada con HTTP y cada capa se puede cambiar sin afectar a las otras.

**3. Ubicación de la regla de disponibilidad (R1, R3).** La consulta (`estaDisponible()`) y el cambio de estado (`marcarPrestado()`) están en `Libro`, porque es quien conoce el dato (GRASP Experto). `PrestamoService` decide qué hacer con esa información: si no está disponible lanza `LibroNoDisponibleException` y el controller responde 409.

**4. Controlador GRASP vs. `@RestController`.** El controlador GRASP del caso de uso es `PrestamoService`: recibe el evento del sistema y coordina a los demás objetos. `PrestamoController` es un adaptador web que traduce HTTP a una llamada del servicio. Por eso un controlador GRASP no equivale necesariamente a un `@RestController`.

**5. Relaciones UML.**
- `Prestamo` → `Estudiante` y `Prestamo` → `Libro` se conservaron como **asociaciones** con multiplicidad `0..*` a `1` (`@ManyToOne`). Un préstamo tiene exactamente un estudiante y un libro, y un libro puede tener muchos préstamos históricos (R5).
- No son composición ni agregación, porque el libro y el estudiante existen aunque no haya préstamo: su ciclo de vida es independiente.
- Las relaciones entre capas son **asociaciones 1 a 1**, porque cada clase guarda la siguiente como atributo (se inyecta por constructor). `PrestamoRequest` es una **dependencia**, porque el controller solo lo usa como parámetro.
- Los repositorios son **interfaces** y declaran solo las operaciones que el caso de uso necesita.

**6. Consistencia (R4).** `registrarPrestamo` es `@Transactional`: cambiar la disponibilidad del libro y crear el préstamo ocurren juntos o no ocurren. Una solicitud inválida (datos faltantes → 400, estudiante o libro inexistente → 404, libro prestado → 409) no crea préstamo.

**7. Trazabilidad requisito → clase → operación → prueba.**

| Requisito | Clase / operación | Prueba |
|---|---|---|
| R1 | `Libro.estaDisponible()` + `PrestamoService` | Libro ya prestado → 409 |
| R2 | `new Prestamo(estudiante, libro, fecha)` | Préstamo registrado → 201 con estudiante, libro y fecha |
| R3 | `Libro.marcarPrestado()` | Después del préstamo el libro queda `disponible: false` |
| R4 | `@Transactional` + validación | 400 / 404 / 409 sin préstamo nuevo |
| R5 | `Prestamo` 0..* → 1 `Libro` | Consulta de préstamos → 200 |
