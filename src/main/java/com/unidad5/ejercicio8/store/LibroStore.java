package com.unidad5.ejercicio8.store;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.unidad5.ejercicio8.exception.NotFoundException;
import com.unidad5.ejercicio8.model.Libro;

@Component
public class LibroStore {

    private final List<Libro> libros = new ArrayList<>();
    private long contadorId = 1;

    public LibroStore() {
        libros.add(new Libro(contadorId++, "Clean Code", "Robert C. Martin"));
        libros.add(new Libro(contadorId++, "Effective Java", "Joshua Bloch"));
    }

    public List<Libro> obtenerTodos() {
        return libros.stream()
                .sorted(Comparator.comparing(Libro::getId))
                .toList();
    }

    public Libro guardar(Libro libro) {
        libro.setId(contadorId++);
        libros.add(libro);
        return libro;
    }

    public Libro buscarPorId(Long id) {
        for (Libro libro : libros) {
            if (libro.getId().equals(id)) {
                return libro;
            }
        }
        throw new NotFoundException("Libro no encontrado");
    }

    public void eliminar(Long id) {
        Libro libro = buscarPorId(id);
        libros.remove(libro);
    }
}
