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

import com.unidad5.ejercicio8.dto.prestamo.PrestamoRequest;
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
    public Prestamo solicitar(@Valid @RequestBody PrestamoRequest request) {
        return prestamoService.solicitarPrestamo(request.libroId(), request.username());
    }

    @GetMapping("/mis-prestamos")
    public List<Prestamo> misPrestamos(@RequestParam String username) {
        return prestamoService.findMine(username);
    }

    @GetMapping
    public List<Prestamo> getAll() {
        return prestamoService.findAll();
    }

    @PutMapping("/{id}/aprobar")
    public Prestamo aprobar(@PathVariable Long id) {
        return prestamoService.aprobar(id);
    }
}
