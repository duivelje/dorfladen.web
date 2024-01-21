package com.steffi.dorfladen.web.monitoring;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DBMonitoringController {
    
    private DatabaseCheckTask dbcTask;
    private static DBMonitoringController instance;

    public static DBMonitoringController getInstance(){
        if(instance == null){
            instance = new DBMonitoringController();
        }
        return instance;
    }

    public void startDBMonitoring(DBMonitoringFrame dbMonFrame){
        //
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        // Setzt einen Task, der alle 10 Minuten ausgeführt wird
        executor.scheduleAtFixedRate(getDatabaseCheckTask(dbMonFrame), 0, 1, TimeUnit.SECONDS);

    }

    private DatabaseCheckTask getDatabaseCheckTask(DBMonitoringFrame dbMonFrame){
        if(dbcTask == null){
            dbcTask = new DatabaseCheckTask(dbMonFrame);
        }
        return dbcTask;
    }

    public void resetDBCheckTime(){
        getDatabaseCheckTask(null).checkNow();
    }
}
