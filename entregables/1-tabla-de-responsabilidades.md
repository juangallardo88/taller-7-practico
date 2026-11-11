# Tabla de responsabilidades — caso de uso «Registrar préstamo»

| Operación o decisión | Clase responsable | Justificación |
|---|---|---|
| Recibir POST /api/prestamos | `PrestamoController` | Capa de presentación: recibe la petición HTTP, valida el formato del `PrestamoRequest` (`@Valid`) y delega al servicio. |
| Buscar estudiante por identificador | `PrestamoService` → `EstudianteRepository.findById()` | El servicio coordina el caso de uso; solo el repositorio accede a los datos. |
| Buscar libro por identificador | `PrestamoService` → `LibroRepository.findById()` | Igual que el anterior. Si el estudiante o el libro no existen, el servicio informa el error y el controller responde 404. |
| Validar disponibilidad | `Libro.estaDisponible()`, invocado por `PrestamoService` | GRASP Experto: el libro conoce su propio estado. El servicio aplica la regla R1 y, si no se cumple, lanza `LibroNoDisponibleException`. |
| Crear el préstamo | `PrestamoService` (`new Prestamo(estudiante, libro, fecha)`) | GRASP Creador: el servicio tiene los datos necesarios para crearlo (estudiante, libro y fecha). Cumple R2. |
| Cambiar disponibilidad | `Libro.marcarPrestado()` | GRASP Experto: el libro cambia su propio estado. Cumple R3. |
| Guardar cambios | `LibroRepository.save()` y `PrestamoRepository.save()` dentro del `@Transactional` del servicio | La persistencia queda en la capa de datos; la transacción garantiza que, si algo falla, no queden cambios a medias (R4). |
| Devolver respuesta HTTP | `PrestamoController` | Traduce el resultado a 201 Created, 404 Not Found o 409 Conflict. |
