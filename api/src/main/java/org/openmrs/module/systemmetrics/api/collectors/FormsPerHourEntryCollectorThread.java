package org.openmrs.module.systemmetrics.api.collectors;


import org.openmrs.api.context.Context;
import org.openmrs.module.systemmetrics.FormsPerHourEntry;
import org.openmrs.module.systemmetrics.api.PerformanceMonitoringService;

/* This thread will run in every one hour and count the number of encounters created
in that hour */

public class FormsPerHourEntryCollectorThread implements Runnable{

    private boolean start;
    FormsPerHourEntry formsPerHourEntry;
    PerformanceMonitoringService performanceMonitoringService;
    int createdEncountersPerHour = 0;

    public FormsPerHourEntryCollectorThread() {
       startFormCountCollectorThread();
    }

    @Override
    public void run() {
        Context.openSession();
        performanceMonitoringService = Context.getService(PerformanceMonitoringService.class);
        while (start){
            createdEncountersPerHour = getCreatedEncounters(System.currentTimeMillis()-3600000,System.currentTimeMillis());
            formsPerHourEntry= new FormsPerHourEntry(System.currentTimeMillis(),4,createdEncountersPerHour);
            performanceMonitoringService.addFormsPerHourEntry(formsPerHourEntry);
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

    public void startFormCountCollectorThread(){
        start = true;
    }

    public void stopFormCountCollectorThread(){
        start = false;
    }
}
