package org.openmrs.module.systemmetrics.api.collectors;


import org.openmrs.api.context.Context;
import org.openmrs.module.systemmetrics.api.PerformanceMonitoringService;

/**
  * This threads runs in every 1 day and delete the previous day's saved Form entries
  * and forms per hours entries from relevant tables.
  */
public class SavedFormEntriesDeletionThread implements Runnable{

    private boolean start;
    private long startTimestamp;
    private long endTimestamp;

    PerformanceMonitoringService performanceMonitoringService;

    public SavedFormEntriesDeletionThread(){
        startSavedFormEntriesDeletionThread();
    }

    @Override
    public void run() {
        Context.openSession();
        performanceMonitoringService = Context.getService(PerformanceMonitoringService.class);
        while (start){
            try {
                Thread.sleep(43200000*2);
            } catch (InterruptedException e) {
                /* ignore*/
            }
            endTimestamp = System.currentTimeMillis();
            performanceMonitoringService.removeSavedFormsWithinTime(startTimestamp, endTimestamp);
        }
    }

    public void startSavedFormEntriesDeletionThread(){
        start = true;
    }

    public void stopSavedFormEntriesDeletionThread(){
        start = false;
    }
}
