package com.unidad5.ejercicio8.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.unidad5.ejercicio8.exception.BadRequestException;
import com.unidad5.ejercicio8.exception.NotFoundException;
import com.unidad5.ejercicio8.model.Libro;
import com.unidad5.ejercicio8.model.Prestamo;
import com.unidad5.ejercicio8.model.PrestamoEstado;

@Service
public class PrestamoService {

    private final AtomicLong sequence = new AtomicLong(0);
    private final ConcurrentHashMap<Long, Prestamo> prestamos = new ConcurrentHashMap<>();
    private final LibroService libroService;

    public PrestamoService(LibroService libroService) {
        this.libroService = libroService;
    }

    public Prestamo solicitarPrestamo(Long libroId, String username) {
        Libro libro = libroService.findById(libroId);
        boolean tienePrestamoActivo = prestamos.values().stream()
                .anyMatch(prestamo -> prestamo.libroId().equals(libroId)
                        && (prestamo.estado() == PrestamoEstado.PENDIENTE
                                || prestamo.estado() == PrestamoEstado.APROBADO));
        if (tienePrestamoActivo) {
            throw new BadRequestException("El libro ya tiene un prestamo activo o pendiente");
        }

        Long id = sequence.incrementAndGet();
        Prestamo prestamo = new Prestamo(
                id,
                libro.id(),
                libro.titulo(),
                username,
                PrestamoEstado.PENDIENTE,
                LocalDateTime.now(),
                null);
        prestamos.put(id, prestamo);
        return prestamo;
    }

    public List<Prestamo> findMine(String username) {
        return prestamos.values().stream()
                .filter(prestamo -> prestamo.usernameLector().equals(username))
                .sorted((a, b) -> a.id().compareTo(b.id()))
                .toList();
    }

    public List<Prestamo> findAll() {
        return prestamos.values().stream()
                .sorted((a, b) -> a.id().compareTo(b.id()))
                .toList();
    }

    public Prestamo aprobar(Long id) {
        Prestamo prestamo = prestamos.get(id);
        if (prestamo == null) {
            throw new NotFoundException("Prestamo no encontrado");
        }
        if (prestamo.estado() == PrestamoEstado.APROBADO) {
            throw new BadRequestException("El prestamo ya fue aprobado");
        }

        Prestamo actualizado = new Prestamo(
                prestamo.id(),
                prestamo.libroId(),
                prestamo.tituloLibro(),
                prestamo.usernameLector(),
                PrestamoEstado.APROBADO,
                prestamo.fechaSolicitud(),
                LocalDateTime.now());
        prestamos.put(id, actualizado);
        return actualizado;
    }
}
