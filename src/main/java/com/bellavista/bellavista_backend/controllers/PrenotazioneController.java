package com.bellavista.bellavista_backend.controllers;


import com.bellavista.bellavista_backend.dto.OspiteDTO;
import com.bellavista.bellavista_backend.dto.PrenotazioneDTO;
import com.bellavista.bellavista_backend.entities.Ospite;
import com.bellavista.bellavista_backend.entities.Prenotazione;
import com.bellavista.bellavista_backend.services.PrenotazioneServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/prenotazioni")
public class PrenotazioneController {

    @Autowired
    private PrenotazioneServices prenotazioneService;

    @GetMapping
    public List<PrenotazioneDTO> getAllPrenotazioni() {
        List<Prenotazione> prenotazioni = prenotazioneService.findAll();
        return prenotazioni.stream().map(prenotazione -> new PrenotazioneDTO(
                prenotazione.getId(),
                prenotazione.getDataInizio(),
                prenotazione.getDataFine(),
                prenotazione.isConfermata(),
                prenotazione.getNote(),
                new OspiteDTO(
                        prenotazione.getOspite().getId(),
                        prenotazione.getOspite().getNome(),
                        prenotazione.getOspite().getCognome(),
                        prenotazione.getOspite().getEmail(),
                        prenotazione.getOspite().getTelefono()
                )
        )).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrenotazioneDTO> getPrenotazioneById(@PathVariable UUID id) {
        Optional<Prenotazione> prenotazioneOptional = prenotazioneService.findById(id);
        if (prenotazioneOptional.isPresent()) {
            Prenotazione prenotazione = prenotazioneOptional.get();
            PrenotazioneDTO prenotazioneDTO = new PrenotazioneDTO(
                    prenotazione.getId(),
                    prenotazione.getDataInizio(),
                    prenotazione.getDataFine(),
                    prenotazione.isConfermata(),
                    prenotazione.getNote(),
                    new OspiteDTO(
                            prenotazione.getOspite().getId(),
                            prenotazione.getOspite().getNome(),
                            prenotazione.getOspite().getCognome(),
                            prenotazione.getOspite().getEmail(),
                            prenotazione.getOspite().getTelefono()
                    )
            );
            return ResponseEntity.ok(prenotazioneDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<PrenotazioneDTO> createPrenotazione(@RequestBody PrenotazioneDTO prenotazioneDTO) {
        Ospite ospite = new Ospite();
        ospite.setId(prenotazioneDTO.getOspite().getId());
        ospite.setNome(prenotazioneDTO.getOspite().getNome());
        ospite.setCognome(prenotazioneDTO.getOspite().getCognome());
        ospite.setEmail(prenotazioneDTO.getOspite().getEmail());
        ospite.setTelefono(prenotazioneDTO.getOspite().getTelefono());

        Prenotazione prenotazione = new Prenotazione();
        prenotazione.setDataInizio(prenotazioneDTO.getDataInizio());
        prenotazione.setDataFine(prenotazioneDTO.getDataFine());
        prenotazione.setConfermata(prenotazioneDTO.isConfermata());
        prenotazione.setNote(prenotazioneDTO.getNote());
        prenotazione.setOspite(ospite);

        Prenotazione savedPrenotazione = prenotazioneService.save(prenotazione);

        PrenotazioneDTO savedPrenotazioneDTO = new PrenotazioneDTO(
                savedPrenotazione.getId(),
                savedPrenotazione.getDataInizio(),
                savedPrenotazione.getDataFine(),
                savedPrenotazione.isConfermata(),
                savedPrenotazione.getNote(),
                new OspiteDTO(
                        savedPrenotazione.getOspite().getId(),
                        savedPrenotazione.getOspite().getNome(),
                        savedPrenotazione.getOspite().getCognome(),
                        savedPrenotazione.getOspite().getEmail(),
                        savedPrenotazione.getOspite().getTelefono()
                )
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(savedPrenotazioneDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PrenotazioneDTO> updatePrenotazione(@PathVariable UUID id, @RequestBody PrenotazioneDTO prenotazioneDTO) {
        Optional<Prenotazione> prenotazioneOptional = prenotazioneService.findById(id);
        if (!prenotazioneOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Prenotazione prenotazione = prenotazioneOptional.get();
        prenotazione.setDataInizio(prenotazioneDTO.getDataInizio());
        prenotazione.setDataFine(prenotazioneDTO.getDataFine());
        prenotazione.setConfermata(prenotazioneDTO.isConfermata());
        prenotazione.setNote(prenotazioneDTO.getNote());

        Prenotazione updatedPrenotazione = prenotazioneService.save(prenotazione);

        PrenotazioneDTO updatedPrenotazioneDTO = new PrenotazioneDTO(
                updatedPrenotazione.getId(),
                updatedPrenotazione.getDataInizio(),
                updatedPrenotazione.getDataFine(),
                updatedPrenotazione.isConfermata(),
                updatedPrenotazione.getNote(),
                new OspiteDTO(
                        updatedPrenotazione.getOspite().getId(),
                        updatedPrenotazione.getOspite().getNome(),
                        updatedPrenotazione.getOspite().getCognome(),
                        updatedPrenotazione.getOspite().getEmail(),
                        updatedPrenotazione.getOspite().getTelefono()
                )
        );

        return ResponseEntity.ok(updatedPrenotazioneDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrenotazione(@PathVariable UUID id) {
        if (!prenotazioneService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        prenotazioneService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

