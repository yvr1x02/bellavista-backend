package com.bellavista.bellavista_backend.repository;

import com.bellavista.bellavista_backend.entities.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PrenotazioneRepository extends JpaRepository <Prenotazione, UUID> {
}
