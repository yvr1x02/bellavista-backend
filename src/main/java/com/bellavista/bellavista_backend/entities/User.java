package com.bellavista.bellavista_backend.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class User {
    @Id
    @GeneratedValue
    private long id;

    private String username;
    private String password;


    private String role;

    @OneToMany(mappedBy = "user")
    private Ospite ospite;


}
