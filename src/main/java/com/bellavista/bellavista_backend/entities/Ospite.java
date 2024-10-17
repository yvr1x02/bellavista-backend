package com.bellavista.bellavista_backend.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "ospiti")
public class Ospite {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "cognome", nullable = false)
    private String cognome;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "telefono")
    private String telefono;

    @JsonManagedReference 
    @OneToMany(mappedBy = "ospite", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Prenotazione> prenotazioni;

    public Ospite() {}

    public Ospite(UUID id, String nome, String cognome, String email, String telefono, List<Prenotazione> prenotazioni) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.telefono = telefono;
        this.prenotazioni = prenotazioni;
    }
}
