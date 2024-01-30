package com.steffi.dorfladen.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.steffi.dorfladen.web.beans.Benutzer;
import com.steffi.dorfladen.web.beans.BenutzerRepository;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class TestController {

    @Autowired
    BenutzerRepository benutzerRepo;
    
    @GetMapping("/benutzer")
    public Benutzer getTestBenutzer() {
        return benutzerRepo.findByBenutzername("Steffi").orElseThrow(() -> new UsernameNotFoundException("Der Benutzer wurde nicht gefunden!"));
    }
}