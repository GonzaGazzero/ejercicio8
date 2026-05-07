package com.unidad5.ejercicio8.model;

import java.util.Set;

public record Usuario(String username, String password, Set<Role> roles) {
}
