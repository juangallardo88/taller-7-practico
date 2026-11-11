package co.edu.ucc.smartlibrary.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Estudiante {

    @Id
    private Long id;
    private String nombre;
    private String correo;

    protected Estudiante() {
        // requerido por JPA
    }
}
