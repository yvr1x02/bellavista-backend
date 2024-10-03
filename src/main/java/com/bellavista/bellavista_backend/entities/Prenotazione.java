package com.bellavista.bellavista_backend.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "prenotazioni")
public class Prenotazione {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "ospite_id")
    private Ospite ospite;

    @ManyToOne
    @JoinColumn(name = "camera_id")
    private Camera camera;

    @Column(name = "data_inizio")
    private LocalDate dataInizio;

    @Column(name = "data_fine")
    private LocalDate dataFine;

    @Column(name = "confermata")
    private boolean confermata;

    public Prenotazione(UUID id, Ospite ospite, LocalDate dataInizio, LocalDate dataFine, boolean confermata) {
        this.id = id;
        this.ospite = ospite;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.confermata = confermata;
    }

}
