package com.unidad5.ejercicio8.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PrestamoRequestDTO {

    @NotNull
    private Long libroId;

    @NotBlank
    private String username;
}
