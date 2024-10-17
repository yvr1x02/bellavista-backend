package com.bellavista.bellavista_backend.controllers;


import com.bellavista.bellavista_backend.entities.Prenotazione;
import com.bellavista.bellavista_backend.services.PrenotazioneServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/prenotazioni")
public class PrenotazioneController {

    @Autowired
    private PrenotazioneServices prenotazioneServices;



    @GetMapping
    public List<Prenotazione> getAllPrenotazioni(){
        return prenotazioneServices.findAll();

    }

}
