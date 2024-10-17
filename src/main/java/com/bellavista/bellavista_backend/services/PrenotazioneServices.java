package com.bellavista.bellavista_backend.services;


import com.bellavista.bellavista_backend.entities.Prenotazione;
import com.bellavista.bellavista_backend.exceptions.InvalidDataException;
import com.bellavista.bellavista_backend.repository.PrenotazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sound.midi.InvalidMidiDataException;
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


    //gestione errori

    private void validatePrenotazione(Prenotazione prenotazione){
        if (prenotazione.getOspite()== null){
            throw new InvalidDataException("L'ospite è obbligatorio");
        }
        if (prenotazione.getDataInizio()==null){
            throw new InvalidDataException("Le date di inizio e fine sono obbligatorie");
        }
        if (prenotazione.getDataInizio().isAfter(prenotazione.getDataFine())) {
            throw new InvalidDataException("La data di inizio non può essere successiva alla data di fine");
        }
    }




}
