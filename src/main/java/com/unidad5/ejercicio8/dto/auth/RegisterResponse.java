package com.unidad5.ejercicio8.dto.auth;

import java.util.Set;

public record RegisterResponse(String username, Set<String> roles) {
}
