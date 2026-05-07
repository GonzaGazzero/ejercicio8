package com.unidad5.ejercicio8.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.unidad5.ejercicio8.dto.auth.AuthResponse;
import com.unidad5.ejercicio8.dto.auth.LoginRequest;
import com.unidad5.ejercicio8.dto.auth.RegisterRequest;
import com.unidad5.ejercicio8.dto.auth.RegisterResponse;
import com.unidad5.ejercicio8.model.Usuario;
import com.unidad5.ejercicio8.security.JwtService;

@Service
public class AuthService {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final long expirationMs;

    public AuthService(
            UserService userService,
            AuthenticationManager authenticationManager,
            JwtService jwtService,
            @Value("${security.jwt.expiration-ms}") long expirationMs) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.expirationMs = expirationMs;
    }

    public RegisterResponse register(RegisterRequest request) {
        Usuario usuario = userService.register(request);
        return new RegisterResponse(
                usuario.username(),
                usuario.roles().stream().map(Enum::name).collect(java.util.stream.Collectors.toSet()));
    }

    public AuthResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password()));
        UserDetails user = (UserDetails) authentication.getPrincipal();
        String token = jwtService.generateToken(user);

        Set<String> roles = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(java.util.stream.Collectors.toSet());
        return new AuthResponse(token, user.getUsername(), roles, expirationMs / 1000);
    }
}
