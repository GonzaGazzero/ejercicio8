package com.unidad5.ejercicio8.security;

import com.unidad5.ejercicio8.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final Map<String, User> users = new HashMap<>();

    public CustomUserDetailsService() {
        // Passwords are "password" encoded with BCrypt
        users.put("admin", User.builder()
                .username("admin")
                .password("$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.TVuHOnu")
                .role(com.unidad5.ejercicio8.model.Role.ROLE_ADMIN)
                .build());
        users.put("biblio", User.builder()
                .username("biblio")
                .password("$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.TVuHOnu")
                .role(com.unidad5.ejercicio8.model.Role.ROLE_BIBLIOTECARIO)
                .build());
        users.put("lector", User.builder()
                .username("lector")
                .password("$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.TVuHOnu")
                .role(com.unidad5.ejercicio8.model.Role.ROLE_LECTOR)
                .build());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = users.get(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }
        return user;
    }

    public void saveUser(User user) {
        users.put(user.getUsername(), user);
    }

    public boolean existsByUsername(String username) {
        return users.containsKey(username);
    }
}

