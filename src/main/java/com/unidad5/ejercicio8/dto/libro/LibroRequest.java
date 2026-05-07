package com.unidad5.ejercicio8.dto.libro;

import jakarta.validation.constraints.NotBlank;

public record LibroRequest(@NotBlank String titulo, @NotBlank String autor) {
}
