package org.openmrs.module.systemmetrics.api.collectors;


import org.openmrs.api.context.Context;
import org.openmrs.module.systemmetrics.VisitsPerHourEntry;
import org.openmrs.module.systemmetrics.api.PerformanceMonitoringService;

/* This thread will run in every one hour and count the number of visits created
in that hour */

public class VisitsPerHourEntryCollectorThread implements Runnable{

    private boolean start;
    VisitsPerHourEntry visitsPerHourEntry;
    PerformanceMonitoringService performanceMonitoringService;
    int createdVisitsPerHour = 0;

    public VisitsPerHourEntryCollectorThread() {
       startVisitCountCollectorThread();
    }

    @Override
    public void run() {
        Context.openSession();
        performanceMonitoringService = Context.getService(PerformanceMonitoringService.class);
        while (start){
            createdVisitsPerHour = getCreatedVisits(System.currentTimeMillis()-3600000,System.currentTimeMillis());
            visitsPerHourEntry= new VisitsPerHourEntry(System.currentTimeMillis(),4,createdVisitsPerHour);
            performanceMonitoringService.addVisitsPerHourEntry(visitsPerHourEntry);
            try {
                Thread.sleep(3600000);
            } catch (InterruptedException e) {
                /* ignore*/
            }
        }

    }

    private int getCreatedVisits(long startTime, long endTime) {
        return performanceMonitoringService.getCreatedVisits(startTime,endTime);
    }

    private boolean isStarted(){
        return start;
    }

    public void startVisitCountCollectorThread(){
        start = true;
    }

    public void stopVisitCountCollectorThread(){
        start = false;
    }
}
