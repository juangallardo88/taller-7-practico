package co.edu.ucc.smartlibrary.service;

/** Mensaje 1.1.8 del diagrama de secuencia: el libro ya esta prestado (R1). */
public class LibroNoDisponibleException extends RuntimeException {

    public LibroNoDisponibleException(Long libroId) {
        super("El libro " + libroId + " no esta disponible");
    }
}
