package com.steffi.dorfladen.web.security;

import java.util.Collections;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.steffi.dorfladen.web.beans.Benutzer;
import com.steffi.dorfladen.web.beans.BenutzerRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private BenutzerRepository repo;

    public CustomUserDetailsService(BenutzerRepository repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Benutzer benutzer = repo.findByBenutzername(username).orElseThrow(
                () -> new UsernameNotFoundException("Den Benutzer " + username + " gibt es nicht in der Datenbank"));

                //TODO: Das bruachen wir dann noch: "GrantedAuthority". Kann man hier mit übergeben^
                //Dies wird vmtl nicht funktionieren, weil das Passwort gehashed ist -> ggf nochmal nachhaken, wie das dann funktioniert
        return new User (benutzer.getBenutzername(), benutzer.getPasswortHashed(), Collections.emptyList());
    }

}
