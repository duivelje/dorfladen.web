package com.steffi.dorfladen.web.monitoring;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

//JFreeChart

/*
 * In diesem Frame möchte ich folgende Dinge darstellen, 
 * - ob die Datenbankverbindung steht (regelmäßigen Ping auf die DB/ Schaltfläche für Direktabfrage)
 * - ob der Webservice läuft
 * - Wer sich wann/ wie oft angemeldet hat
 * - wie viele ZUgriffe es auf welche Daten gab
 * Außerdem soll es eine Möglichkeit geben, Benutzer zu pflegen <- hier wäre es professionell, wenn man sein Passwort mit der ersten ANmeldung ändern muss ACHTUNG! Sicherheit!
 * 
 */
public class MonitoringFrame extends JFrame {

    private JDesktopPane contentPanel;
    private static MonitoringFrame instance;

    private DBMonitoringFrame dbMonitoringFrame;

    private MonitoringFrame() {
        // Frame initialisieren
        setNimbusLookAndFeel();
        initialize();
    }

    public static MonitoringFrame getInstance() {
        if (instance == null) {
            instance = new MonitoringFrame();
        }
        return instance;
    }

    private void initialize() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(1320, 1000);
        setTitle("Dorfladen Immensen - Monitoring Backend");
        setLocationRelativeTo(null);
        setContentPane(getContentPanel());
    }

    private JDesktopPane getContentPanel() {
        if (contentPanel == null) {
            contentPanel = new JDesktopPane();
            contentPanel.setLayout(new java.awt.BorderLayout());
            contentPanel.setDoubleBuffered(true);
            var content = getDBMonitoringFrame();
            contentPanel.add(content, java.awt.BorderLayout.CENTER);
            // panelStack.add(content);
        }
        return contentPanel;
    }

    public DBMonitoringFrame getDBMonitoringFrame() {
        if (dbMonitoringFrame == null) {
            dbMonitoringFrame = new DBMonitoringFrame();
        }
        return dbMonitoringFrame;
    }

    

    private static void setNimbusLookAndFeel() {
        for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                        | UnsupportedLookAndFeelException e) {
                    // TODO ordentliches Logging!
                    System.out.println("Fehler beim Setzen des Look An Feels");
                }
                break;
            }
        }
    }

}
