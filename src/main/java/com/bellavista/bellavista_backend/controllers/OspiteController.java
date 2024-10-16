package com.bellavista.bellavista_backend.controllers;


import com.bellavista.bellavista_backend.entities.Ospite;
import com.bellavista.bellavista_backend.services.OspiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/ospiti")

public class OspiteController {


    @Autowired
    private OspiteService ospiteService;

    @GetMapping
    public List<Ospite> getAllOspiti(){
        return ospiteService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ospite> getOspiteById(@PathVariable UUID id) {
        Optional<Ospite> ospite = ospiteService.findById(id);
        return ospite.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Ospite createOspite(@RequestBody Ospite ospite) {
        return ospiteService.save(ospite);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ospite> updateOspite(@PathVariable UUID id, @RequestBody Ospite ospite) {
        if (ospiteService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        ospite.setId(id);
        return ResponseEntity.ok(ospiteService.save(ospite));
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
