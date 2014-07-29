package org.openmrs.module.systemmetrics.api.collectors;


import org.openmrs.api.context.Context;
import org.openmrs.module.systemmetrics.api.PerformanceMonitoringService;

/**
 * This threads runs in every 30 minute and delete the last 30 minutes' logged in user count data entries from
 * systemmetrics_login_value table.
 */
public class LoggedInUsersDeletionThread implements Runnable{

    private boolean start;
    private long startTimestamp;
    private long endTimestamp;

    PerformanceMonitoringService performanceMonitoringService;

    public LoggedInUsersDeletionThread() {
        startLoggedInUsersDeletionThread();
    }

    @Override
    public void run() {
        Context.openSession();
        performanceMonitoringService = Context.getService(PerformanceMonitoringService.class);
        while (start){
            startTimestamp = System.currentTimeMillis();
            try {
                Thread.sleep(60000*5*6);
            } catch (InterruptedException e) {
                /* ignore*/
            }
            endTimestamp = System.currentTimeMillis();
            performanceMonitoringService.removeLoginValuesWithinTime(startTimestamp, endTimestamp);
        }
    }

    public void startLoggedInUsersDeletionThread(){
        start = true;
    }

    public void stopLoggedInUsersDeletionThread(){
        start = false;
    }
}
