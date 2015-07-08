package org.openmrs.module.systemmetrics.api.collectors;


import org.openmrs.api.context.Context;
import org.openmrs.module.systemmetrics.api.PerformanceMonitoringService;

/**
  * This threads runs in every 1 day and delete the previous day's saved concept entries
  * and forms per hours entries from relevant tables.
  */
public class SavedConceptEntriesDeletionThread implements Runnable{

    private boolean start;
    private long startTimestamp;
    private long endTimestamp;

    PerformanceMonitoringService performanceMonitoringService;

    public SavedConceptEntriesDeletionThread(){
        startSavedConceptEntriesDeletionThread();
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
            performanceMonitoringService.removeSavedConceptsWithinTime(startTimestamp, endTimestamp);
        }
    }

    public void startSavedConceptEntriesDeletionThread(){
        start = true;
    }

    public void stopSavedConceptEntriesDeletionThread(){
        start = false;
    }
}
