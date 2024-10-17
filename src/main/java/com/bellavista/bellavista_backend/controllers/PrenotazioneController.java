package com.bellavista.bellavista_backend.controllers;


import com.bellavista.bellavista_backend.entities.Prenotazione;
import com.bellavista.bellavista_backend.services.PrenotazioneServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/prenotazioni")
public class PrenotazioneController {

    @Autowired
    private PrenotazioneServices prenotazioneServices;



    @GetMapping
    public List<Prenotazione> getAllPrenotazioni(){
        return prenotazioneServices.findAll();

    }

    @GetMapping("/{id}")
    public ResponseEntity<Prenotazione> getPrenotazioneById(@PathVariable UUID id) {
        Optional<Prenotazione> prenotazione = prenotazioneServices.findById(id);
        return prenotazione.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }



    @PostMapping
    public Prenotazione creaPrenotazione(@RequestBody Prenotazione prenotazione){
        return prenotazioneServices.save(prenotazione);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Prenotazione> updatePrenotazione(@PathVariable UUID id, @RequestBody Prenotazione prenotazione) {
        if (prenotazioneServices.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        prenotazione.setId(id);
        return ResponseEntity.ok(prenotazioneServices.save(prenotazione));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrenotazione(@PathVariable UUID id) {
        if (prenotazioneServices.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        prenotazioneServices.delete(id);
        return ResponseEntity.noContent().build();
    }

}
