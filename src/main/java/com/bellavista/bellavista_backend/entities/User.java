package com.bellavista.bellavista_backend.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue
    private long id;

    private String username;
    private String password;


    private String role;

    @OneToOne(mappedBy = "user")
    private Ospite ospite;


}
