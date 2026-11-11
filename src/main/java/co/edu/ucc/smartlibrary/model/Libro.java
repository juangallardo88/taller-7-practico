package co.edu.ucc.smartlibrary.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY,
        getterVisibility = JsonAutoDetect.Visibility.NONE,
        isGetterVisibility = JsonAutoDetect.Visibility.NONE)
public class Libro {

    @Id
    private Long id;
    private String titulo;
    private boolean disponible;

    protected Libro() {
        // requerido por JPA
    }

    /** R1: solo un libro disponible puede prestarse. */
    public boolean estaDisponible() {
        return disponible;
    }

    /** R3: al prestarlo, el libro deja de estar disponible. */
    public void marcarPrestado() {
        this.disponible = false;
    }
}
