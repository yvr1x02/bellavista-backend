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

    @OneToMany(mappedBy = "ospite", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Prenotazione> prenotazioni;


}
