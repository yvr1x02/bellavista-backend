package com.bellavista.bellavista_backend.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "ospiti")
public class Ospite {

    @Id
    @GeneratedValue
    private UUID id;


    @Column(name = "nome")
    private String nome;

    @Column(name = "cognome")
    private String cognome;

    @Column(name = "email")
    private String email;

    @Column(name = "telefono")
    private String telefono;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;



    @OneToMany(mappedBy = "ospite", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Prenotazione> prenotazioni;


    public Ospite() {
    }

    public Ospite(UUID id, String nome, String cognome, String email, String telefono, List<Prenotazione> prenotazioni) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.telefono = telefono;
        this.prenotazioni = prenotazioni;
    }
}
