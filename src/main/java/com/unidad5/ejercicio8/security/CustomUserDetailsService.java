package com.unidad5.ejercicio8.security;

public final class CustomUserDetailsService {

    private CustomUserDetailsService() {
    }

    /*
     * GUIA DE RESOLUCION
     *
     * Esta clase normalmente implementa UserDetailsService.
     *
     * Objetivo:
     * - Buscar un usuario por username.
     * - Convertirlo a UserDetails con sus authorities.
     *
     * Como el ejercicio pide trabajar en memoria, se puede conectar con un servicio
     * que mantenga usuarios en un Map<String, Usuario>.
     */
}
