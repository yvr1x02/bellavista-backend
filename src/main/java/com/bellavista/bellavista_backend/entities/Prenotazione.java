package com.bellavista.bellavista_backend.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "prenotazioni")
public class Prenotazione {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @JsonBackReference
    @JoinColumn(name = "ospite_id", nullable = false)
    private Ospite ospite;

    @Column(name = "data_inizio", nullable = false)
    private LocalDate dataInizio;

    @Column(name = "data_fine", nullable = false)
    private LocalDate dataFine;

    @Column(name = "confermata", nullable = false)
    private boolean confermata;

    @Column(name = "note")
    private String note;

    public Prenotazione() {}

    public Prenotazione(UUID id, Ospite ospite, LocalDate dataInizio, LocalDate dataFine, boolean confermata, String note) {
        this.id = id;
        this.ospite = ospite;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.confermata = confermata;
        this.note = note;
    }
}
