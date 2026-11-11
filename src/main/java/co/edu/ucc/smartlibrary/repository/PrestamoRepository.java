package co.edu.ucc.smartlibrary.repository;

import co.edu.ucc.smartlibrary.model.Prestamo;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface PrestamoRepository extends Repository<Prestamo, Long> {

    Prestamo save(Prestamo prestamo);

    List<Prestamo> findAll();
}
