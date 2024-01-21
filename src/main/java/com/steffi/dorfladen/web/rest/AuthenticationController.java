package com.steffi.dorfladen.web.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginCredentials credentials) {
        // Implementieren Sie hier die Logik zur Überprüfung der Anmeldedaten.
        // Normalerweise würde hier eine Überprüfung mit einer Benutzerdatenbank oder einem Authentifizierungsdienst stattfinden.

        boolean isAuthenticated = authenticateUser(credentials);

        //Hier sinnvolles Logging und Ausgabe im Monitor über Listener
        

        if (isAuthenticated) {
            return ResponseEntity.ok("Anmeldung erfolgreich");
        } else {
            return ResponseEntity.status(401).body("Anmeldung fehlgeschlagen");
        }
    }

    private boolean authenticateUser(LoginCredentials credentials) {
        // Hier sollten Sie die tatsächliche Authentifizierungslogik implementieren.
        // Dies ist nur ein Platzhalter für die Demonstration.
        return "user".equals(credentials.getUsername()) && "password".equals(credentials.getPassword());
    }

}