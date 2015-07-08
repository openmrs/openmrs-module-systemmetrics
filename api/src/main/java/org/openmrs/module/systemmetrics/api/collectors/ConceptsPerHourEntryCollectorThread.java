package org.openmrs.module.systemmetrics.api.collectors;


import org.openmrs.api.context.Context;
import org.openmrs.module.systemmetrics.ConceptsPerHourEntry;
import org.openmrs.module.systemmetrics.api.PerformanceMonitoringService;

/* This thread will run in every one hour and count the number of concepts created
in that hour */

public class ConceptsPerHourEntryCollectorThread implements Runnable{

    private boolean start;
    ConceptsPerHourEntry conceptsPerHourEntry;
    PerformanceMonitoringService performanceMonitoringService;
    int createdConceptsPerHour = 0;

    public ConceptsPerHourEntryCollectorThread() {
       startConceptCountCollectorThread();
    }

    @Override
    public void run() {
        Context.openSession();
        performanceMonitoringService = Context.getService(PerformanceMonitoringService.class);
        while (start){
            createdConceptsPerHour = getCreatedConcepts(System.currentTimeMillis()-3600000,System.currentTimeMillis());
            conceptsPerHourEntry= new ConceptsPerHourEntry(System.currentTimeMillis(),4,createdConceptsPerHour);
            performanceMonitoringService.addConceptsPerHourEntry(conceptsPerHourEntry);
            try {
                Thread.sleep(3600000);
            } catch (InterruptedException e) {
                /* ignore*/
            }
        }

    }

    private int getCreatedConcepts(long startTime, long endTime) {
        return performanceMonitoringService.getCreatedConcepts(startTime,endTime);
    }

    private boolean isStarted(){
        return start;
    }

    public void startConceptCountCollectorThread(){
        start = true;
    }

    public void stopConceptCountCollectorThread(){
        start = false;
    }
}
