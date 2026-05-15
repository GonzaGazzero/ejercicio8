package com.unidad5.ejercicio8.service;

import com.unidad5.ejercicio8.dto.AuthRequest;
import com.unidad5.ejercicio8.dto.AuthResponse;
import com.unidad5.ejercicio8.dto.RegisterRequest;
import com.unidad5.ejercicio8.model.Role;
import com.unidad5.ejercicio8.model.User;
import com.unidad5.ejercicio8.security.CustomUserDetailsService;
import com.unidad5.ejercicio8.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final CustomUserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public void register(RegisterRequest request) {
        var user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_LECTOR)
                .build();
        userDetailsService.saveUser(user);
    }

    public AuthResponse login(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = userDetailsService.loadUserByUsername(request.getUsername());
        var jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }
}
