package com.steffi.dorfladen.web.beans;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogEintraegeRepository extends CrudRepository<LogEintraege, Long> {

    
}
