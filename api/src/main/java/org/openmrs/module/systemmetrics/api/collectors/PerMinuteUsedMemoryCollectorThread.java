package org.openmrs.module.systemmetrics.api.collectors;

import org.openmrs.api.context.Context;
import org.openmrs.module.systemmetrics.MetricValue;
import org.openmrs.module.systemmetrics.api.PerMinMetricValue;
import org.openmrs.module.systemmetrics.api.PerformanceMonitoringService;

public class PerMinuteUsedMemoryCollectorThread implements Runnable {

    private boolean start;
    private MetricValue perMinMemoryValue;
    private long startTimestamp;
    private long endTimestamp;

    PerformanceMonitoringService performanceMonitoringService;

    public PerMinuteUsedMemoryCollectorThread() {

      startPerMinMemoryCollector();
    }

    @Override
    public void run() {
        Context.openSession();
        performanceMonitoringService = Context.getService(PerformanceMonitoringService.class);
        while (start){
            startTimestamp = System.currentTimeMillis();
            try {
                Thread.sleep(60000);
            } catch (InterruptedException e) {
                /* ignore*/
            }
            endTimestamp = System.currentTimeMillis();
            PerMinMetricValue perMinValue = performanceMonitoringService.getMetricValue(startTimestamp,endTimestamp);
            performanceMonitoringService.addPerMinMetricValue(perMinValue);
           // System.out.println("PerMinuteUsedMemoryCollectorThread  added " + perMinValue.getMetricValue() + "- " + perMinValue.getTimestamp());

        }
    }

    public void startPerMinMemoryCollector(){
        start = true;
    }

    public void stopPerMinMemoryCollector(){
        start = false;
    }


}
