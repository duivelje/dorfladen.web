package com.steffi.dorfladen.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.steffi.dorfladen.web.beans.BenutzerRepository;
import com.steffi.dorfladen.web.beans.LogEintragService;
import com.steffi.dorfladen.web.security.JWTTokenProvider;
import com.steffi.dorfladen.web.security.PasswortHashing;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class AuthenticationController {

    @Autowired
    private BenutzerRepository repo;

    @Autowired
    private LogEintragService logServe;

    @Autowired
    private AuthenticationManager authManager;
    
    @Autowired
    private JWTTokenProvider provider;
    
    private final PasswortHashing passwortHashing;

    public AuthenticationController(PasswortHashing passwortHashing) {
        this.passwortHashing = passwortHashing;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginCredentials credentials) {

        logServe.createAndPrepairLogEintrag(credentials.getUsername());

        var authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        credentials.getUsername(),
                        credentials.getPassword()));

        logServe.saveAktLog(true);

        return ResponseEntity.ok(provider.generateToken(authentication));

        // // Implementieren Sie hier die Logik zur Überprüfung der Anmeldedaten.
        // // Normalerweise würde hier eine Überprüfung mit einer Benutzerdatenbank oder
        // // einem Authentifizierungsdienst stattfinden.

        // boolean isAuthenticated = authenticateUser(credentials);

        // // TODO: Ausgabe im Monitor über Listener, Logging ggf um die IP-Adresse des
        // // Aufrufers erweitern, wenn das geht

        // // TODO: EMail, wenn zu viele nicht erfolgreiche Authentifizierungsversuche

        // if (isAuthenticated) {
        //     return ResponseEntity.ok("Anmeldung erfolgreich");
        // } else {
        //     return ResponseEntity.status(401).body("Anmeldung fehlgeschlagen");
        // }
    }

    private boolean authenticateUser(LoginCredentials credentials) {
        var benutzer = repo.findByBenutzername(credentials.getUsername()).orElse(null);
        if (benutzer == null) {
            return false;
        }
        return passwortHashing.isPasswordKorrekt(credentials.getPassword(), benutzer);
    }

}