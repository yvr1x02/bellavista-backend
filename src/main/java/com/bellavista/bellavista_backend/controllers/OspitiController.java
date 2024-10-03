package com.bellavista.bellavista_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bellavista.bellavista_backend.entities.Ospite;
import com.bellavista.bellavista_backend.repository.OspiteRepository;

@RestController
public class OspitiController {
    @Autowired
    private OspiteRepository ospiteRepository;

    @PostMapping("/ospite")
    public String createOspite() {
        Ospite ospite = new Ospite();
        ospite.setNome("Mario");
        ospite.setCognome("Rossi");
        ospite.setEmail("mario.rossi@example.com");
        ospiteRepository.save(ospite);
        return "Ospite creato!";
    }
}