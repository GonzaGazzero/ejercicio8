package com.unidad5.ejercicio8.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Prestamo {

    private Long id;
    private Long libroId;
    private String tituloLibro;
    private String usernameLector;
    private PrestamoEstado estado;
    private LocalDateTime fechaSolicitud;
    private LocalDateTime fechaAprobacion;
}
