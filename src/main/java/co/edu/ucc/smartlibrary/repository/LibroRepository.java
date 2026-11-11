package co.edu.ucc.smartlibrary.repository;

import co.edu.ucc.smartlibrary.model.Libro;
import org.springframework.data.repository.Repository;

public interface LibroRepository extends Repository<Libro, Long> {

    /** Devuelve null si no existe. */
    Libro findById(Long id);

    Libro save(Libro libro);
}
