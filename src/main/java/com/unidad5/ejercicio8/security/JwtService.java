package com.unidad5.ejercicio8.security;

public final class JwtService {

    private JwtService() {
    }

    /*
     * GUIA DE RESOLUCION
     *
     * Responsabilidades esperadas:
     * - Generar el token JWT al hacer login.
     * - Extraer el username desde el token.
     * - Validar expiracion y firma.
     *
     * Metodos que normalmente se agregan:
     * - generateToken(UserDetails userDetails)
     * - extractUsername(String token)
     * - isTokenValid(String token, UserDetails userDetails)
     *
     * Tambien suele leerse desde application.properties:
     * - secret
     * - expirationMs
     */
}
