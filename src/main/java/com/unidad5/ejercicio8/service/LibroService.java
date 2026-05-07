package com.unidad5.ejercicio8.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.unidad5.ejercicio8.dto.LibroRequestDTO;
import com.unidad5.ejercicio8.model.Libro;
import com.unidad5.ejercicio8.store.LibroStore;

@Service
public class LibroService {

    private final LibroStore libroStore;

    public LibroService(LibroStore libroStore) {
        this.libroStore = libroStore;
    }

    public List<Libro> findAll() {
        return libroStore.obtenerTodos();
    }

    public Libro create(LibroRequestDTO request) {
        Libro libro = new Libro();
        libro.setTitulo(request.getTitulo());
        libro.setAutor(request.getAutor());
        return libroStore.guardar(libro);
    }

    public void delete(Long id) {
        libroStore.eliminar(id);
    }

    public Libro findById(Long id) {
        return libroStore.buscarPorId(id);
    }
}
