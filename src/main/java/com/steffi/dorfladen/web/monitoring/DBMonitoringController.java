package com.steffi.dorfladen.web.monitoring;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

@Service
public class DBMonitoringController {
    
    private final DatabaseCheckTask dbcTask;

    public DBMonitoringController(DatabaseCheckTask task){
        this.dbcTask = task;
    }

    public void startDBMonitoring(DBMonitoringFrame dbMonFrame){
        //
        dbcTask.setDBMonitopringFrame(dbMonFrame);
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        // Setzt einen Task, der alle 10 Minuten ausgeführt wird
        executor.scheduleAtFixedRate(dbcTask, 0, 1, TimeUnit.SECONDS);

    }

    public void resetDBCheckTime(){
        dbcTask.checkNow();
    }
}
