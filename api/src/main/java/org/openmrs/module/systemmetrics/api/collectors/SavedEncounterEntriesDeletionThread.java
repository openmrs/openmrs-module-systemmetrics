package org.openmrs.module.systemmetrics.api.collectors;


import org.openmrs.api.context.Context;
import org.openmrs.module.systemmetrics.api.PerformanceMonitoringService;

/**
  * This threads runs in every 1 day and delete the previous day's saved encounter entries
  * and forms per hours entries from relevant tables.
  */
public class SavedEncounterEntriesDeletionThread implements Runnable{

    private boolean start;
    private long startTimestamp;
    private long endTimestamp;

    PerformanceMonitoringService performanceMonitoringService;

    public SavedEncounterEntriesDeletionThread(){
        startSavedEncounterEntriesDeletionThread();
    }

    @Override
    public void run() {
        Context.openSession();
        performanceMonitoringService = Context.getService(PerformanceMonitoringService.class);
        while (start){
            startTimestamp = System.currentTimeMillis();
            try {
                Thread.sleep(43200000*2);
            } catch (InterruptedException e) {
                /* ignore*/
            }
            endTimestamp = System.currentTimeMillis();
            performanceMonitoringService.removeSavedEncountersWithinTime(startTimestamp, endTimestamp);
        }
    }

    public void startSavedEncounterEntriesDeletionThread(){
        start = true;
    }

    public void stopSavedEncounterEntriesDeletionThread(){
        start = false;
    }
}
