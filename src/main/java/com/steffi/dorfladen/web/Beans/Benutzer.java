package com.steffi.dorfladen.web.Beans;

import com.steffi.dorfladen.web.Datenbank.PasswordHashing;

public class Benutzer {

    // Private Felder
    private String benutzername;
    private String passwortHashed;

    // Öffentlicher Konstruktor ohne Parameter
    public Benutzer() {
    }

    // Getter- und Setter-Methoden

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
        this.passwortHashed = PasswordHashing.encrypt(rawPasswort);
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
