package com.unidad5.ejercicio8.dto.prestamo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PrestamoRequest(@NotNull Long libroId, @NotBlank String username) {
}
