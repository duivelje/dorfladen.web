package com.steffi.dorfladen.web.beans;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDateTime;
import java.sql.Timestamp;

@Service
public class LogEintragService {

    @Autowired
    LogEintragRepository logRep;

    private LogEintrag aktLog;

    public void createAndPrepairLogEintrag(String username) {
        var newLog = new LogEintrag();
        newLog.setSource("Authentifizierungsversuch");
        newLog.setDetails(username);
        newLog.setZeitpunkt(Timestamp.valueOf(LocalDateTime.now()));
        aktLog = newLog;
    }
    
    public void saveAktLog(boolean wasSuccessfull){
        if(aktLog != null){
            aktLog.setDetails(aktLog.getDetails() + (wasSuccessfull ? " - erfolgreich" : " - nicht erfolgreich"));
            logRep.save(aktLog);
            aktLog = null;
        }
    }
}