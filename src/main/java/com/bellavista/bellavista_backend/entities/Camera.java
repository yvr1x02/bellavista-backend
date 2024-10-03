package com.bellavista.bellavista_backend.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "camere")
public class Camera {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "descrizione")
    private String descrizione;

    @Column(name = "numero_posti_letto")
    private int numeroPostiLetto;

    @OneToMany(mappedBy = "camera")
    private List<Prenotazione> prenotazioni;

    public Camera(UUID id, String nome, String descrizione, int numeroPostiLetto, List<Prenotazione> prenotazioni) {
        this.id = id;
        this.nome = nome;
        this.descrizione = descrizione;
        this.numeroPostiLetto = numeroPostiLetto;
        this.prenotazioni = prenotazioni;
    }

}

