package com.bellavista.bellavista_backend.controllers;

import com.bellavista.bellavista_backend.entities.User;
import com.bellavista.bellavista_backend.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private UserServices userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User userRequest) {
        // Controlla se l'username esiste già
        if (userService.findByUsername(userRequest.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Username già in uso.");
        }

        // Codifica la password
        userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));

        // Imposta il ruolo
        if ("adminUser".equals(userRequest.getUsername())) {
            userRequest.setRole("ADMIN");
        } else {
            userRequest.setRole("USER");
        }

        // Salva l'utente
        User savedUser = userService.save(userRequest);

        return ResponseEntity.ok("Utente registrato con successo.");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User userRequest) {
        Optional<User> user = userService.findByUsername(userRequest.getUsername());

        if (user.isPresent() && passwordEncoder.matches(userRequest.getPassword(), user.get().getPassword())) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Login effettuato con successo");
            response.put("role", user.get().getRole());
            response.put("username", user.get().getUsername());// Restituisci il ruolo dell'utente
            return ResponseEntity.ok(response);  // Restituisci la risposta come JSON
        }

        return ResponseEntity.status(401).body(Map.of("error", "Credenziali non valide"));
    }

    @PostMapping("/register-admin")
    public ResponseEntity<?> registerAdmin() {
        // Crea un nuovo utente admin
        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("adminPass"));  // Imposta la password codificata
        admin.setRole("ADMIN");  // Imposta il ruolo admin

        // Salva l'admin nel database
        userService.save(admin);

        return ResponseEntity.ok("Admin creato con successo.");
    }

}
