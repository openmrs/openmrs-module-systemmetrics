package org.openmrs.module.systemmetrics.api.collectors;


import org.openmrs.api.context.Context;
import org.openmrs.module.systemmetrics.PatientsPerHourEntry;
import org.openmrs.module.systemmetrics.api.PerformanceMonitoringService;

/* This thread will run in every one hour and count the number of patients created
in that hour */

public class PatientsPerHourEntryCollectorThread implements Runnable{

    private boolean start;
    PatientsPerHourEntry patientsPerHourEntry;
    PerformanceMonitoringService performanceMonitoringService;
    int createdPatientsPerHour = 0;

    public PatientsPerHourEntryCollectorThread() {
       startPatientCountCollectorThread();
    }

    @Override
    public void run() {
        Context.openSession();
        performanceMonitoringService = Context.getService(PerformanceMonitoringService.class);
        while (start){
            createdPatientsPerHour = getCreatedPatients(System.currentTimeMillis()-3600000,System.currentTimeMillis());
            patientsPerHourEntry= new PatientsPerHourEntry(System.currentTimeMillis(),6,createdPatientsPerHour);
            performanceMonitoringService.addPatientsPerHourEntry(patientsPerHourEntry);
            try {
                Thread.sleep(3600000);
            } catch (InterruptedException e) {
                /* ignore*/
            }
        }

    }

    private int getCreatedPatients(long startTime, long endTime) {
        return performanceMonitoringService.getCreatedPatients(startTime,endTime);
    }

    private boolean isStarted(){
        return start;
    }

    public void startPatientCountCollectorThread(){
        start = true;
    }

    public void stopPatientCountCollectorThread(){
        start = false;
    }
}
