package org.openmrs.module.systemmetrics.api.collectors;


import org.openmrs.api.context.Context;
import org.openmrs.module.systemmetrics.EncountersPerHourEntry;
import org.openmrs.module.systemmetrics.api.PerformanceMonitoringService;

/* This thread will run in every one hour and count the number of encounters created
in that hour */

public class EncountersPerHourEntryCollectorThread implements Runnable{

    private boolean start;
    EncountersPerHourEntry encountersPerHourEntry;
    PerformanceMonitoringService performanceMonitoringService;
    int createdEncountersPerHour = 0;

    public EncountersPerHourEntryCollectorThread() {
       startEncounterCountCollectorThread();
    }

    @Override
    public void run() {
        Context.openSession();
        performanceMonitoringService = Context.getService(PerformanceMonitoringService.class);
        while (start){
            createdEncountersPerHour = getCreatedEncounters(System.currentTimeMillis()-3600000,System.currentTimeMillis());
            encountersPerHourEntry= new EncountersPerHourEntry(System.currentTimeMillis(),4,createdEncountersPerHour);
            performanceMonitoringService.addEncountersPerHourEntry(encountersPerHourEntry);
            try {
                Thread.sleep(3600000);
            } catch (InterruptedException e) {
                /* ignore*/
            }
        }

    }

    private int getCreatedEncounters(long startTime, long endTime) {
        return performanceMonitoringService.getCreatedEncounters(startTime,endTime);
    }

    private boolean isStarted(){
        return start;
    }

    public void startEncounterCountCollectorThread(){
        start = true;
    }

    public void stopEncounterCountCollectorThread(){
        start = false;
    }
}
