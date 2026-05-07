package com.unidad5.ejercicio8.store;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.unidad5.ejercicio8.exception.NotFoundException;
import com.unidad5.ejercicio8.model.Prestamo;

@Component
public class PrestamoStore {

    private final List<Prestamo> prestamos = new ArrayList<>();
    private long contadorId = 1;

    public Prestamo guardar(Prestamo prestamo) {
        prestamo.setId(contadorId++);
        prestamos.add(prestamo);
        return prestamo;
    }

    public List<Prestamo> obtenerTodos() {
        return prestamos.stream()
                .sorted(Comparator.comparing(Prestamo::getId))
                .toList();
    }

    public Prestamo buscarPorId(Long id) {
        for (Prestamo prestamo : prestamos) {
            if (prestamo.getId().equals(id)) {
                return prestamo;
            }
        }
        throw new NotFoundException("Prestamo no encontrado");
    }

    public List<Prestamo> buscarPorUsername(String username) {
        return prestamos.stream()
                .filter(prestamo -> prestamo.getUsernameLector().equals(username))
                .sorted(Comparator.comparing(Prestamo::getId))
                .toList();
    }

    public boolean existePrestamoActivoParaLibro(Long libroId) {
        for (Prestamo prestamo : prestamos) {
            if (prestamo.getLibroId().equals(libroId)) {
                return true;
            }
        }
        return false;
    }
}
