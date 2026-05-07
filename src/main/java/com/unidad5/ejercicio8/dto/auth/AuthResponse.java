package com.unidad5.ejercicio8.dto.auth;

import java.util.Set;

public record AuthResponse(String token, String username, Set<String> roles, long expiresInSeconds) {
}
