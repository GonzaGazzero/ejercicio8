package com.unidad5.ejercicio8.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.unidad5.ejercicio8.dto.LibroRequestDTO;
import com.unidad5.ejercicio8.model.Libro;
import com.unidad5.ejercicio8.service.LibroService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

    private final LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    // ENDPOINT 1: GET /api/libros
    // Retorna todos los libros cargados en memoria.
    @GetMapping
    public List<Libro> getAll() {
        return libroService.findAll();
    }

    // ENDPOINT 2: POST /api/libros
    // Crea un nuevo libro en memoria.
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Libro create(@Valid @RequestBody LibroRequestDTO request) {
        return libroService.create(request);
    }

    // ENDPOINT 3: DELETE /api/libros/{id}
    // Elimina un libro por id.
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        libroService.delete(id);
        return "Libro eliminado correctamente";
    }
}
