package org.openmrs.module.systemmetrics.api.collectors;


import org.openmrs.api.context.Context;
import org.openmrs.module.systemmetrics.ReportsPerHourEntry;
import org.openmrs.module.systemmetrics.api.PerformanceMonitoringService;

/* This thread will run in every one hour and count the number of reports created
in that hour */

public class ReportsPerHourEntryCollectorThread implements Runnable{

    private boolean start;
    ReportsPerHourEntry reportsPerHourEntry;
    PerformanceMonitoringService performanceMonitoringService;
    int createdReportsPerHour = 0;

    public ReportsPerHourEntryCollectorThread() {
       startReportCountCollectorThread();
    }

    @Override
    public void run() {
        Context.openSession();
        performanceMonitoringService = Context.getService(PerformanceMonitoringService.class);
        while (start){
            createdReportsPerHour = getCreatedReports(System.currentTimeMillis()-3600000,System.currentTimeMillis());
            reportsPerHourEntry= new ReportsPerHourEntry(System.currentTimeMillis(),4,createdReportsPerHour);
            performanceMonitoringService.addReportsPerHourEntry(reportsPerHourEntry);
            try {
                Thread.sleep(3600000);
            } catch (InterruptedException e) {
                /* ignore*/
            }
        }

    }

    private int getCreatedReports(long startTime, long endTime) {
        return performanceMonitoringService.getCreatedReports(startTime,endTime);
    }

    private boolean isStarted(){
        return start;
    }

    public void startReportCountCollectorThread(){
        start = true;
    }

    public void stopReportCountCollectorThread(){
        start = false;
    }
}
