package org.openmrs.module.systemmetrics.api.collectors;


import org.openmrs.api.context.Context;
import org.openmrs.module.systemmetrics.FormsPerHourEntry;
import org.openmrs.module.systemmetrics.api.PerformanceMonitoringService;

/* This thread will run in every one hour and count the number of forms created
in that hour */

public class FormsPerHourEntryCollectorThread implements Runnable{

    private boolean start;
    FormsPerHourEntry formsPerHourEntry;
    PerformanceMonitoringService performanceMonitoringService;
    int createdFormsPerHour = 0;

    public FormsPerHourEntryCollectorThread() {
       startFormCountCollectorThread();
    }

    @Override
    public void run() {
        Context.openSession();
        performanceMonitoringService = Context.getService(PerformanceMonitoringService.class);
        while (start){
            createdFormsPerHour = getCreatedForms(System.currentTimeMillis()-3600000,System.currentTimeMillis());
            formsPerHourEntry= new FormsPerHourEntry(System.currentTimeMillis(),8,createdFormsPerHour);
            performanceMonitoringService.addFormsPerHourEntry(formsPerHourEntry);
            try {
                Thread.sleep(3600000);
            } catch (InterruptedException e) {
                /* ignore*/
            }
        }

    }

    private int getCreatedForms(long startTime, long endTime) {
        return performanceMonitoringService.getCreatedForms(startTime,endTime);
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
