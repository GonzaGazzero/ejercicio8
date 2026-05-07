package com.unidad5.ejercicio8.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.unidad5.ejercicio8.dto.PrestamoRequestDTO;
import com.unidad5.ejercicio8.model.Prestamo;
import com.unidad5.ejercicio8.service.PrestamoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/prestamos")
public class PrestamoController {

    private final PrestamoService prestamoService;

    public PrestamoController(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    // ENDPOINT 4: POST /api/prestamos
    // Solicita un prestamo indicando libro y username.
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Prestamo solicitar(@Valid @RequestBody PrestamoRequestDTO request) {
        return prestamoService.solicitarPrestamo(request.getLibroId(), request.getUsername());
    }

    // ENDPOINT 5: GET /api/prestamos/mis-prestamos
    // Retorna los prestamos del usuario indicado.
    @GetMapping("/mis-prestamos")
    public List<Prestamo> misPrestamos(@RequestParam String username) {
        return prestamoService.findMine(username);
    }

    // ENDPOINT 6: GET /api/prestamos
    // Retorna todos los prestamos cargados.
    @GetMapping
    public List<Prestamo> getAll() {
        return prestamoService.findAll();
    }

    // ENDPOINT 7: PUT /api/prestamos/{id}/aprobar
    // Cambia el estado del prestamo a APROBADO.
    @PutMapping("/{id}/aprobar")
    public Prestamo aprobar(@PathVariable Long id) {
        return prestamoService.aprobar(id);
    }
}
