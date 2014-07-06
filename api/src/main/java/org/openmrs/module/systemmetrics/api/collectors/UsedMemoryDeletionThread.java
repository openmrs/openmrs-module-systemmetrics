package org.openmrs.module.systemmetrics.api.collectors;

import org.openmrs.api.context.Context;
import org.openmrs.module.systemmetrics.api.PerformanceMonitoringService;

/**
 * This threads runs in every 30 minute and delete the last 30 minutes' used memory data entries from
 * systemmetrics_metric_value table.
 */
public class UsedMemoryDeletionThread implements Runnable{

    private boolean start;
    private long startTimestamp;
    private long endTimestamp;

    PerformanceMonitoringService performanceMonitoringService;

    public UsedMemoryDeletionThread() {
       startUsedMemoryDeletionThread();
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
            performanceMonitoringService.removeMetricValuesInPreviousMins(startTimestamp, endTimestamp);
        }
    }

    public void startUsedMemoryDeletionThread(){
        start = true;
    }

    public void stopUsedMemoryDeletionThread(){
        start = false;
    }
}
