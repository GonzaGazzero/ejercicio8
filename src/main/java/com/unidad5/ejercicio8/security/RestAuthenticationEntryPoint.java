package com.unidad5.ejercicio8.security;

public final class RestAuthenticationEntryPoint {

    private RestAuthenticationEntryPoint() {
    }

    /*
     * GUIA DE RESOLUCION
     *
     * Esta pieza se usa para responder 401 cuando el usuario no esta autenticado
     * o el token es invalido.
     *
     * Habitualmente implementa AuthenticationEntryPoint y escribe una respuesta JSON
     * con datos como:
     * - timestamp
     * - status
     * - error
     * - message
     * - path
     */
}
