package com.steffi.dorfladen.web.monitoring;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import org.springframework.stereotype.Component;

import com.steffi.dorfladen.web.util.Util;

@Component
public class DBMonitoringFrame extends JInternalFrame {

    private JButton testDBConnectionButton;
    private JButton addBenutzerButton;
    private JLabel counterDataLabel;
    private JLabel connectionDataLabel;
    private JPanel contentPanel;
    private final DBMonitoringController controller;

    public DBMonitoringFrame(DBMonitoringController controller) {
        super();
        this.controller = controller;
        init();
        show();
    }

    protected void startDBMonitoring() {
        controller.startDBMonitoring(this);
    }

    @Override
    public void setBackground(Color bg) {
        if (contentPanel == null) {
            super.setBackground(bg);

        } else {
            contentPanel.setBackground(bg);
        }
    }

    private void init() {
        contentPanel = new JPanel();
        contentPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
        contentPanel.setBackground(Util.C_NOT_CONN);
        contentPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbcConnectionLabel = new GridBagConstraints();
        gbcConnectionLabel.gridx = 0;
        gbcConnectionLabel.gridy = 0;
        gbcConnectionLabel.anchor = GridBagConstraints.WEST;
        gbcConnectionLabel.insets = new Insets(0, 0, 10, 0);
        var p1 = new JLabel("Connection zur Datenbank ");
        contentPanel.add(p1, gbcConnectionLabel);
        GridBagConstraints gbcConnectionDataLabel = new GridBagConstraints();
        gbcConnectionDataLabel.gridx = 1;
        gbcConnectionDataLabel.gridy = 0;
        gbcConnectionDataLabel.insets = new Insets(0, 0, 10, 0);
        contentPanel.add(getConnectionDataLabel(), gbcConnectionDataLabel);
        GridBagConstraints gbcCounterLabel = new GridBagConstraints();
        gbcCounterLabel.gridx = 0;
        gbcCounterLabel.gridy = 1;
        gbcCounterLabel.anchor = GridBagConstraints.WEST;
        gbcCounterLabel.insets = new Insets(0, 0, 10, 10);
        var p2 = new JLabel("Zeigt bis zum nächsten Ping: ");
        contentPanel.add(p2, gbcCounterLabel);
        GridBagConstraints gbcCounterDataLabel = new GridBagConstraints();
        gbcCounterDataLabel.gridx = 1;
        gbcCounterDataLabel.gridy = 1;
        gbcCounterDataLabel.insets = new Insets(0, 0, 10, 0);
        contentPanel.add(getCounterDataLabel(), gbcCounterDataLabel);
        GridBagConstraints gbcTestDBConnButton = new GridBagConstraints();
        gbcTestDBConnButton.gridx = 0;
        gbcTestDBConnButton.gridy = 2;
        gbcTestDBConnButton.gridwidth = 2;
        contentPanel.add(getTestDBConnectionButton(), gbcTestDBConnButton);

        add(contentPanel);

        // Auswahl-Kasten-Panel erstmal ausblenden, sieht iwie kacke aus
        ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).setNorthPane(null);

    }

    private JButton getTestDBConnectionButton() {
        if (testDBConnectionButton == null) {
            testDBConnectionButton = new JButton("DB-Connection JETZT testen");
            testDBConnectionButton.addActionListener(a -> {
                controller.resetDBCheckTime();
            });
        }
        return testDBConnectionButton;
    }

    private JButton getAddBenutzerButton() {
        if (addBenutzerButton == null) {
            addBenutzerButton = new JButton("Benutzerverwaltung");
            addBenutzerButton.addActionListener(a -> {
                //
            });
        }
        return addBenutzerButton;
    }

    public JLabel getConnectionDataLabel() {
        if (connectionDataLabel == null) {
            connectionDataLabel = new JLabel("besteht nicht");
        }
        return connectionDataLabel;
    }

    public JLabel getCounterDataLabel() {
        if (counterDataLabel == null) {
            counterDataLabel = new JLabel("--:--");
        }
        return counterDataLabel;
    }
}
