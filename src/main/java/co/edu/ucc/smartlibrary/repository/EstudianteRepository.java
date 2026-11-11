package co.edu.ucc.smartlibrary.repository;

import co.edu.ucc.smartlibrary.model.Estudiante;
import org.springframework.data.repository.Repository;

public interface EstudianteRepository extends Repository<Estudiante, Long> {

    /** Devuelve null si no existe. */
    Estudiante findById(Long id);
}
