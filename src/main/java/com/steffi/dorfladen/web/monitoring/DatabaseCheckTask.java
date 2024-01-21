package com.steffi.dorfladen.web.monitoring;

import java.sql.SQLException;

import com.steffi.dorfladen.web.util.ConnectionUtil;
import com.steffi.dorfladen.web.util.Util;

class DatabaseCheckTask implements Runnable {

    private DBMonitoringFrame dbMonFrame;
    private static final int CHECK_INTERVAL_SECONDS = 600;
    private static int secondsUntilNextCheck = CHECK_INTERVAL_SECONDS;

    public DatabaseCheckTask(DBMonitoringFrame dbMonFrame) {
        this.dbMonFrame = dbMonFrame;
        checkDatabaseConnection();
    }

    private DatabaseCheckTask() {
        // Darf nicht ohne Frame aufgerufen werden, wäre sonst witzlos
    }

    @Override
    public void run() {
        secondsUntilNextCheck--;

        // Anzeige der verbleibenden Zeit bis zur nächsten Überprüfung
        dbMonFrame.getCounterDataLabel().setText(secondsUntilNextCheck/60 + ":" + secondsUntilNextCheck%60);

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
            if (ConnectionUtil.getConnection() != null && ConnectionUtil.getConnection().isValid(2)) {
                isConnected = true;
            }
        } catch (SQLException e) {
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

    public void checkNow(){
        secondsUntilNextCheck = 0;
    }
}
