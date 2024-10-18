package com.bellavista.bellavista_backend.controllers;


import com.bellavista.bellavista_backend.entities.User;
import com.bellavista.bellavista_backend.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private UserServices userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User userRequest) {
        // Controlla se l'username esiste già
        if (userService.findByUsername(userRequest.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Username già in uso.");
        }

        // Imposta il ruolo in base a condizioni
        if ("adminUser".equals(userRequest.getUsername())) {
            userRequest.setRole("ADMIN");
        } else {
            userRequest.setRole("USER");
        }

        // Salva l'utente
        User savedUser = userService.save(userRequest);

        return ResponseEntity.ok("Utente registrato con successo.");
    }
}
