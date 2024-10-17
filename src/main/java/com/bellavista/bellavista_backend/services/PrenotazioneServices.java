package com.bellavista.bellavista_backend.services;


import com.bellavista.bellavista_backend.entities.Prenotazione;
import com.bellavista.bellavista_backend.repository.PrenotazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PrenotazioneServices {


    @Autowired
    private PrenotazioneRepository prenotazioneRepository;



    //findAll
    public List<Prenotazione> findAll(){
        return prenotazioneRepository.findAll();
    }


    //findById
    public Optional<Prenotazione> findById(UUID id){
        return prenotazioneRepository.findById(id);
    }

    //save
   public Prenotazione save(Prenotazione prenotazione){
        return prenotazioneRepository.save(prenotazione);
   }

    //delete
    public void delete(UUID id){
        prenotazioneRepository.deleteById(id);
    }




}
