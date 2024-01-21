package com.steffi.dorfladen.web;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
// import org.springframework.boot.SpringApplication;
// import org.springframework.boot.autoconfigure.domain.EntityScan;
// import org.springframework.context.annotation.ComponentScan;
// import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

// import com.steffi.dorfladen.web.beans.Benutzer;
// import com.steffi.dorfladen.web.beans.BenutzerRepository;
import com.steffi.dorfladen.web.monitoring.DBMonitoringController;
import com.steffi.dorfladen.web.monitoring.MonitoringFrame;

@SpringBootApplication
public class DorfladenBackend {
    
    public static void main(String[] args) {
        //TODO: Passwörter wieder sicher machen
        SpringApplicationBuilder builder = new SpringApplicationBuilder(DorfladenBackend.class);
        builder.headless(false);
        ConfigurableApplicationContext context = builder.run(args);

        //Erstmal Frame aufbauen
        var frame = MonitoringFrame.getInstance();
        //Hier könnte man noch Dinge tun, wenn man wöllte

        frame.setVisible(true);

        // Jetzt die DB-Connection
        DBMonitoringController.getInstance().startDBMonitoring(frame.getDBMonitoringFrame());

        
        // Benutzer steffi = new Benutzer();
        // steffi.setBenutzername("Steffi");
        // steffi.setPasswortHashed("testitest");

        // BenutzerRepository rep = appContext.getBean(BenutzerRepository.class);
        // rep.save(steffi);
    }
}
