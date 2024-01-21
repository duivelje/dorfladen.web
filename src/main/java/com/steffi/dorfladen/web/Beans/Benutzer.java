package com.steffi.dorfladen.web.beans;

import com.steffi.dorfladen.web.util.PasswortHashing;

import jakarta.persistence.*;


@Entity
@Table(name = "benutzer")
public class Benutzer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Private Felder
    private String benutzername;

    @Column(name = "passwort_hash")
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

    public void setPasswortHashed(String rawPasswort) {
        this.passwortHashed = PasswortHashing.encrypt(rawPasswort);
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
