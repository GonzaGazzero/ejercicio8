package com.unidad5.ejercicio8.security;

public final class SecurityConfig {

    private SecurityConfig() {
    }

    /*
     * GUIA DE RESOLUCION
     *
     * Este archivo deberia concentrar la configuracion principal de Spring Security.
     *
     * Pasos sugeridos:
     * 1. Anotar la clase con @Configuration.
     * 2. Habilitar seguridad web y seguridad a nivel metodo si el ejercicio lo pide.
     * 3. Declarar un bean SecurityFilterChain.
     * 4. Desactivar CSRF si la API es stateless.
     * 5. Configurar SessionCreationPolicy.STATELESS.
     * 6. Declarar que /api/auth/register y /api/auth/login sean publicos.
     * 7. Restringir /api/libros y /api/prestamos por rol.
     * 8. Registrar el filtro JWT antes de UsernamePasswordAuthenticationFilter.
     * 9. Configurar AuthenticationEntryPoint y AccessDeniedHandler para 401 y 403.
     *
     * En esta entrega no se implementa seguridad real.
     */
}
