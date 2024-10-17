package com.bellavista.bellavista_backend.controllers;


import com.bellavista.bellavista_backend.dto.OspiteDTO;
import com.bellavista.bellavista_backend.entities.Ospite;
import com.bellavista.bellavista_backend.services.OspiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/ospiti")
public class OspiteController {

    @Autowired
    private OspiteService ospiteService;

    @GetMapping
    public List<OspiteDTO> getAllOspiti() {
        List<Ospite> ospiti = ospiteService.findAll();
        return ospiti.stream()
                .map(ospite -> new OspiteDTO(
                        ospite.getId(),
                        ospite.getNome(),
                        ospite.getCognome(),
                        ospite.getEmail(),
                        ospite.getTelefono()
                ))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OspiteDTO> getOspiteById(@PathVariable UUID id) {
        Optional<Ospite> ospiteOptional = ospiteService.findById(id);
        if (ospiteOptional.isPresent()) {
            Ospite ospite = ospiteOptional.get();
            OspiteDTO ospiteDTO = new OspiteDTO(
                    ospite.getId(),
                    ospite.getNome(),
                    ospite.getCognome(),
                    ospite.getEmail(),
                    ospite.getTelefono()
            );
            return ResponseEntity.ok(ospiteDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<OspiteDTO> createOspite(@RequestBody OspiteDTO ospiteDTO) {
        Ospite ospite = new Ospite();
        ospite.setNome(ospiteDTO.getNome());
        ospite.setCognome(ospiteDTO.getCognome());
        ospite.setEmail(ospiteDTO.getEmail());
        ospite.setTelefono(ospiteDTO.getTelefono());

        Ospite savedOspite = ospiteService.save(ospite);

        OspiteDTO savedOspiteDTO = new OspiteDTO(
                savedOspite.getId(),
                savedOspite.getNome(),
                savedOspite.getCognome(),
                savedOspite.getEmail(),
                savedOspite.getTelefono()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(savedOspiteDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OspiteDTO> updateOspite(@PathVariable UUID id, @RequestBody OspiteDTO ospiteDTO) {
        Optional<Ospite> ospiteOptional = ospiteService.findById(id);
        if (ospiteOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Ospite ospite = ospiteOptional.get();
        ospite.setNome(ospiteDTO.getNome());
        ospite.setCognome(ospiteDTO.getCognome());
        ospite.setEmail(ospiteDTO.getEmail());
        ospite.setTelefono(ospiteDTO.getTelefono());

        Ospite updatedOspite = ospiteService.save(ospite);

        OspiteDTO updatedOspiteDTO = new OspiteDTO(
                updatedOspite.getId(),
                updatedOspite.getNome(),
                updatedOspite.getCognome(),
                updatedOspite.getEmail(),
                updatedOspite.getTelefono()
        );

        return ResponseEntity.ok(updatedOspiteDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOspite(@PathVariable UUID id) {
        if (ospiteService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        ospiteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

