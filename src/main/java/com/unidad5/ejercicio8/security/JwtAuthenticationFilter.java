package com.unidad5.ejercicio8.security;

public final class JwtAuthenticationFilter {

    private JwtAuthenticationFilter() {
    }

    /*
     * GUIA DE RESOLUCION
     *
     * Este archivo normalmente extiende OncePerRequestFilter.
     *
     * Flujo habitual:
     * 1. Leer header Authorization.
     * 2. Verificar que empiece con "Bearer ".
     * 3. Extraer token.
     * 4. Obtener username con JwtService.
     * 5. Cargar UserDetails.
     * 6. Validar token.
     * 7. Si es valido, guardar Authentication en SecurityContextHolder.
     * 8. Continuar con filterChain.doFilter(...).
     */
}
