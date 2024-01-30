package com.steffi.dorfladen.web.monitoring;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.stereotype.Component;

import com.steffi.dorfladen.web.util.Util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Component
public class DatabaseCheckTask implements Runnable {

    private static final int CHECK_INTERVAL_SECONDS = 600;
    private static int secondsUntilNextCheck = 0;

    private DBMonitoringFrame dbMonFrame;

    // private DataSource dataSource;

    // public DatabaseCheckTask(DataSource dataSource) {
    // this.dataSource = dataSource;
    // // checkDatabaseConnection();
    // }

    @PersistenceContext
    private EntityManager entityManager;

    // public Connection getConnection() {
    // try (var con = dataSource.getConnection()) {
    // return con;
    // // Verwenden Sie das Connection-Objekt
    // } catch (SQLException e) {
    // // Fehlerbehandlung
    // }
    // return null;
    // }

    public void setDBMonitopringFrame(DBMonitoringFrame dbFrame) {
        dbMonFrame = dbFrame;
    }

    @Override
    public void run() {
        secondsUntilNextCheck--;

        // Anzeige der verbleibenden Zeit bis zur nächsten Überprüfung
        dbMonFrame.getCounterDataLabel().setText(secondsUntilNextCheck / 60 + ":" + secondsUntilNextCheck % 60);

        if (secondsUntilNextCheck <= 0) {
            // Zurücksetzen des Timers für die nächste Überprüfung
            secondsUntilNextCheck = CHECK_INTERVAL_SECONDS;

            // Datenbanküberprüfung durchführen
            checkDatabaseConnection();
        }
    }

    private void checkDatabaseConnection() {
        var isConnected = false;
        try {
            var benutzer = entityManager.createNativeQuery("SELECT benutzername FROM dorfladen.benutzer")
                    .getResultList();
            System.out.println(benutzer.iterator().next().toString());
            isConnected = true;
        } catch (Exception e) {
            System.out.println("Abfrage nicht erfolgreich");
            // HIER nichts machen, dann is eben nicht connected
        }
        // GUI aktualisieren
        if (isConnected) {
            dbMonFrame.setBackground(Util.C_CONN);
            dbMonFrame.getConnectionDataLabel().setText(Util.S_CONN);
            dbMonFrame.repaint();
        } else {
            dbMonFrame.setBackground(Util.C_NOT_CONN);
            dbMonFrame.getConnectionDataLabel().setText(Util.S_NOT_CONN);
            dbMonFrame.repaint();
        }
        System.out.println("check");
    }

    public void checkNow() {
        secondsUntilNextCheck = 0;
    }
}
