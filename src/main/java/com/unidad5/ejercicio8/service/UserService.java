package com.unidad5.ejercicio8.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.unidad5.ejercicio8.dto.auth.RegisterRequest;
import com.unidad5.ejercicio8.exception.ConflictException;
import com.unidad5.ejercicio8.model.Role;
import com.unidad5.ejercicio8.model.Usuario;

@Service
public class UserService {

    private final ConcurrentHashMap<String, Usuario> users = new ConcurrentHashMap<>();
    private final PasswordEncoder passwordEncoder;

    public UserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        seedUsers();
    }

    public Usuario register(RegisterRequest request) {
        String username = request.username().trim().toLowerCase();
        if (users.containsKey(username)) {
            throw new ConflictException("El usuario ya existe");
        }

        Usuario usuario = new Usuario(
                username,
                passwordEncoder.encode(request.password()),
                Set.of(Role.ROLE_LECTOR));
        users.put(username, usuario);
        return usuario;
    }

    public Optional<Usuario> findByUsername(String username) {
        return Optional.ofNullable(users.get(username.toLowerCase()));
    }

    public List<Usuario> findAll() {
        return users.values().stream().toList();
    }

    private void seedUsers() {
        saveSeed("lector", "lector123", Set.of(Role.ROLE_LECTOR));
        saveSeed("biblio", "biblio123", Set.of(Role.ROLE_BIBLIOTECARIO));
        saveSeed("admin", "admin123", Set.of(Role.ROLE_ADMIN));
    }

    private void saveSeed(String username, String password, Set<Role> roles) {
        users.put(username, new Usuario(username, passwordEncoder.encode(password), roles));
    }
}
