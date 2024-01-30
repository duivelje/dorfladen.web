package com.steffi.dorfladen.web.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;


import jakarta.persistence.*;


@Entity
@Table(name = "benutzer")
public class Benutzer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Private Felder
    @Column(unique = true)
    private String benutzername;

    @Column(name = "passwort_hash")
    @JsonIgnore
    private String passwortHashed;


    public String getBenutzername() {
        return benutzername;
    }

    public void setBenutzername(String benutzername) {
        this.benutzername = benutzername;
    }

    public String getPasswortHashed() {
        return passwortHashed;
    }

    public void setPasswortHashdded(String hashedPasswort) {
        this.passwortHashed = hashedPasswort;
    }

    // Optional: Überschreiben von toString() zur besseren Ausgabe
    @Override
    public String toString() {
        return "Benutzer{" +
                "benutzername='" + benutzername + '\'' +
                ", passwortHashed='" + passwortHashed + '\'' +
                '}';
    }
    
}
