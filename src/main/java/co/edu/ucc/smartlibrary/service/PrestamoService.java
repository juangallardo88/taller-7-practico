package co.edu.ucc.smartlibrary.service;

import co.edu.ucc.smartlibrary.model.Estudiante;
import co.edu.ucc.smartlibrary.model.Libro;
import co.edu.ucc.smartlibrary.model.Prestamo;
import co.edu.ucc.smartlibrary.repository.EstudianteRepository;
import co.edu.ucc.smartlibrary.repository.LibroRepository;
import co.edu.ucc.smartlibrary.repository.PrestamoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class PrestamoService {

    private final EstudianteRepository estudianteRepository;
    private final LibroRepository libroRepository;
    private final PrestamoRepository prestamoRepository;

    public PrestamoService(EstudianteRepository estudianteRepository,
                           LibroRepository libroRepository,
                           PrestamoRepository prestamoRepository) {
        this.estudianteRepository = estudianteRepository;
        this.libroRepository = libroRepository;
        this.prestamoRepository = prestamoRepository;
    }

    /**
     * Caso de uso "Registrar prestamo" (ver diagrama de secuencia).
     * Es transaccional: si algo falla no se cambia la disponibilidad ni se crea el prestamo (R4).
     */
    @Transactional
    public Prestamo registrarPrestamo(Long estudianteId, Long libroId) {
        Estudiante estudiante = estudianteRepository.findById(estudianteId);   // 1.1.1
        if (estudiante == null) {
            throw new IllegalArgumentException("Estudiante no encontrado: " + estudianteId);
        }

        Libro libro = libroRepository.findById(libroId);                      // 1.1.2
        if (libro == null) {
            throw new IllegalArgumentException("Libro no encontrado: " + libroId);
        }

        if (!libro.estaDisponible()) {                                       // 1.1.3 (R1)
            throw new LibroNoDisponibleException(libroId);                   // 1.1.8
        }

        libro.marcarPrestado();                                              // 1.1.4 (R3)
        libroRepository.save(libro);                                         // 1.1.5
        return prestamoRepository.save(
                new Prestamo(estudiante, libro, LocalDate.now()));          // 1.1.6 (R2)
    }

    public List<Prestamo> listarPrestamos() {
        return prestamoRepository.findAll();
    }
}
