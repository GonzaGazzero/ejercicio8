package com.unidad5.ejercicio8.security;

public final class RestAccessDeniedHandler {

    private RestAccessDeniedHandler() {
    }

    /*
     * GUIA DE RESOLUCION
     *
     * Esta clase se usa para responder 403 cuando el usuario esta autenticado
     * pero no tiene permisos suficientes.
     *
     * Habitualmente implementa AccessDeniedHandler y devuelve un JSON similar
     * al del AuthenticationEntryPoint.
     */
}
