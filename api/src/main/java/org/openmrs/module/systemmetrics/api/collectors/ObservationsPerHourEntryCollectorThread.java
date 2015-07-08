package org.openmrs.module.systemmetrics.api.collectors;


import org.openmrs.api.context.Context;
import org.openmrs.module.systemmetrics.ObservationsPerHourEntry;
import org.openmrs.module.systemmetrics.api.PerformanceMonitoringService;

/* This thread will run in every one hour and count the number of observations created
in that hour */

public class ObservationsPerHourEntryCollectorThread implements Runnable{

    private boolean start;
    ObservationsPerHourEntry observationsPerHourEntry;
    PerformanceMonitoringService performanceMonitoringService;
    int createdObservationsPerHour = 0;

    public ObservationsPerHourEntryCollectorThread() {
       startObservationCountCollectorThread();
    }

    @Override
    public void run() {
        Context.openSession();
        performanceMonitoringService = Context.getService(PerformanceMonitoringService.class);
        while (start){
            createdObservationsPerHour = getCreatedObservations(System.currentTimeMillis()-3600000,System.currentTimeMillis());
            observationsPerHourEntry= new ObservationsPerHourEntry(System.currentTimeMillis(),4,createdObservationsPerHour);
            performanceMonitoringService.addObservationsPerHourEntry(observationsPerHourEntry);
            try {
                Thread.sleep(3600000);
            } catch (InterruptedException e) {
                /* ignore*/
            }
        }

    }

    private int getCreatedObservations(long startTime, long endTime) {
        return performanceMonitoringService.getCreatedObservations(startTime,endTime);
    }

    private boolean isStarted(){
        return start;
    }

    public void startObservationCountCollectorThread(){
        start = true;
    }

    public void stopObservationCountCollectorThread(){
        start = false;
    }
}
