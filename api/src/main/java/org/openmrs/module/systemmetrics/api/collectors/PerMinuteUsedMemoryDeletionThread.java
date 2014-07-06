package org.openmrs.module.systemmetrics.api.collectors;

import org.openmrs.api.context.Context;
import org.openmrs.module.systemmetrics.api.PerformanceMonitoringService;

/**
 * This threads runs in every 5 hours and delete the last 5 hour used memory data entries from
 * systemmetrics_permin_metric_value table.
 */
public class PerMinuteUsedMemoryDeletionThread implements Runnable{

    private boolean start;
    private long startTimestamp;
    private long endTimestamp;

    PerformanceMonitoringService performanceMonitoringService;

    public PerMinuteUsedMemoryDeletionThread() {
        startPerminUsedMemoryDeletionThread();
    }

    @Override
    public void run() {
        Context.openSession();
        performanceMonitoringService = Context.getService(PerformanceMonitoringService.class);
        while (start){
            startTimestamp = System.currentTimeMillis();
            try {
                Thread.sleep(60000*60*5);
            } catch (InterruptedException e) {
                /* ignore*/
            }
            endTimestamp = System.currentTimeMillis();
            performanceMonitoringService.removePerminuteMetricValuesInPreviousHours(startTimestamp, endTimestamp);
        }
    }

    public void startPerminUsedMemoryDeletionThread(){
        start = true;
    }

    public void stopPerminUsedMemoryDeletionThread(){
        start = false;
    }
}
