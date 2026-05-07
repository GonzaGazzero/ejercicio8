package com.unidad5.ejercicio8.service;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.unidad5.ejercicio8.dto.libro.LibroRequest;
import com.unidad5.ejercicio8.exception.NotFoundException;
import com.unidad5.ejercicio8.model.Libro;

@Service
public class LibroService {

    private final AtomicLong sequence = new AtomicLong(0);
    private final ConcurrentHashMap<Long, Libro> libros = new ConcurrentHashMap<>();

    public LibroService() {
        create(new LibroRequest("Clean Code", "Robert C. Martin"));
        create(new LibroRequest("Effective Java", "Joshua Bloch"));
    }

    public List<Libro> findAll() {
        return libros.values().stream().sorted((a, b) -> a.id().compareTo(b.id())).toList();
    }

    public Libro create(LibroRequest request) {
        Long id = sequence.incrementAndGet();
        Libro libro = new Libro(id, request.titulo(), request.autor());
        libros.put(id, libro);
        return libro;
    }

    public void delete(Long id) {
        if (libros.remove(id) == null) {
            throw new NotFoundException("Libro no encontrado");
        }
    }

    public Libro findById(Long id) {
        Libro libro = libros.get(id);
        if (libro == null) {
            throw new NotFoundException("Libro no encontrado");
        }
        return libro;
    }
}
