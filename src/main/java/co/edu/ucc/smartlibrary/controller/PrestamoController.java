package co.edu.ucc.smartlibrary.controller;

import co.edu.ucc.smartlibrary.dto.PrestamoRequest;
import co.edu.ucc.smartlibrary.service.LibroNoDisponibleException;
import co.edu.ucc.smartlibrary.service.PrestamoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/prestamos")
public class PrestamoController {

    private final PrestamoService prestamoService;

    public PrestamoController(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    /** POST /api/prestamos -> 201 Created | 404 Not Found | 409 Conflict */
    @PostMapping
    public ResponseEntity<?> registrar(@Valid @RequestBody PrestamoRequest req) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(prestamoService.registrarPrestamo(req.estudianteId(), req.libroId()));
        } catch (LibroNoDisponibleException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /** GET /api/prestamos -> 200 OK */
    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(prestamoService.listarPrestamos());
    }
}
