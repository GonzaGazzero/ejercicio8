package com.unidad5.ejercicio8.model;

import java.time.LocalDateTime;

public record Prestamo(
        Long id,
        Long libroId,
        String tituloLibro,
        String usernameLector,
        PrestamoEstado estado,
        LocalDateTime fechaSolicitud,
        LocalDateTime fechaAprobacion) {
}
