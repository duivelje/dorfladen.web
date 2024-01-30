package com.steffi.dorfladen.web.beans;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BenutzerRepository extends CrudRepository<Benutzer, Long> {

    Optional<Benutzer> findByBenutzername(String benutzername);
    
}
