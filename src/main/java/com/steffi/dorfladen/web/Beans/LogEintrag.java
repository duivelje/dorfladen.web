package com.steffi.dorfladen.web.beans;

import java.sql.Timestamp;

import jakarta.persistence.*;

@Entity
@Table(name = "Logs")
public class LogEintrag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String source;

    @Column(length = Integer.MAX_VALUE)
    private String details;

    private Timestamp zeitpunkt;

    @Column(length = Integer.MAX_VALUE)
    private String bemerkung;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Timestamp getZeitpunkt() {
        return zeitpunkt;
    }

    public void setZeitpunkt(Timestamp zeitpunkt) {
        this.zeitpunkt = zeitpunkt;
    }

    public String getBemerkung() {
        return bemerkung;
    }

    public void setBemerkung(String bemerkung) {
        this.bemerkung = bemerkung;
    }

    // Optional: Überschreiben der toString-Methode für besseres Debugging
    @Override
    public String toString() {
        return "Logs{" +
                "id=" + id +
                ", source='" + source + '\'' +
                ", details='" + details + '\'' +
                ", zeitpunkt=" + zeitpunkt +
                ", bemerkung='" + bemerkung + '\'' +
                '}';
    }
    
}
