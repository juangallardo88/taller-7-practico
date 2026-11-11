package co.edu.ucc.smartlibrary.dto;

import jakarta.validation.constraints.NotNull;

/** Objeto de entrada de POST /api/prestamos. */
public record PrestamoRequest(
        @NotNull Long estudianteId,
        @NotNull Long libroId) {
}
