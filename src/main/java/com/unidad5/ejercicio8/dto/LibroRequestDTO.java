package com.unidad5.ejercicio8.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LibroRequestDTO {

    @NotBlank
    private String titulo;

    @NotBlank
    private String autor;
}
