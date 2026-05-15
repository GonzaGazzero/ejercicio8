package com.unidad5.ejercicio8.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('LECTOR')")
    public Prestamo solicitar(@Valid @RequestBody PrestamoRequestDTO request, Authentication authentication) {
        return prestamoService.solicitarPrestamo(request.getLibroId(), authentication.getName());
    }

    @GetMapping("/mis-prestamos")
    @PreAuthorize("hasRole('LECTOR')")
    public List<Prestamo> misPrestamos(Authentication authentication) {
        return prestamoService.findMine(authentication.getName());
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('BIBLIOTECARIO', 'ADMIN')")
    public List<Prestamo> getAll() {
        return prestamoService.findAll();
    }

    @PutMapping("/{id}/aprobar")
    @PreAuthorize("hasRole('BIBLIOTECARIO')")
    public Prestamo aprobar(@PathVariable Long id) {
        return prestamoService.aprobar(id);
    }
}
