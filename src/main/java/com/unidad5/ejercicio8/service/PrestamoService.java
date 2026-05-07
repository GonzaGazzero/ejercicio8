package com.unidad5.ejercicio8.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.unidad5.ejercicio8.exception.BadRequestException;
import com.unidad5.ejercicio8.model.Libro;
import com.unidad5.ejercicio8.model.Prestamo;
import com.unidad5.ejercicio8.model.PrestamoEstado;
import com.unidad5.ejercicio8.store.PrestamoStore;

@Service
public class PrestamoService {

    private final LibroService libroService;
    private final PrestamoStore prestamoStore;

    public PrestamoService(LibroService libroService, PrestamoStore prestamoStore) {
        this.libroService = libroService;
        this.prestamoStore = prestamoStore;
    }

    public Prestamo solicitarPrestamo(Long libroId, String username) {
        Libro libro = libroService.findById(libroId);
        if (prestamoStore.existePrestamoActivoParaLibro(libroId)) {
            throw new BadRequestException("El libro ya tiene un prestamo activo o pendiente");
        }

        Prestamo prestamo = new Prestamo();
        prestamo.setLibroId(libro.getId());
        prestamo.setTituloLibro(libro.getTitulo());
        prestamo.setUsernameLector(username);
        prestamo.setEstado(PrestamoEstado.PENDIENTE);
        prestamo.setFechaSolicitud(LocalDateTime.now());
        prestamo.setFechaAprobacion(null);
        return prestamoStore.guardar(prestamo);
    }

    public List<Prestamo> findMine(String username) {
        return prestamoStore.buscarPorUsername(username);
    }

    public List<Prestamo> findAll() {
        return prestamoStore.obtenerTodos();
    }

    public Prestamo aprobar(Long id) {
        Prestamo prestamo = prestamoStore.buscarPorId(id);
        if (prestamo.getEstado() == PrestamoEstado.APROBADO) {
            throw new BadRequestException("El prestamo ya fue aprobado");
        }

        prestamo.setEstado(PrestamoEstado.APROBADO);
        prestamo.setFechaAprobacion(LocalDateTime.now());
        return prestamo;
    }
}
