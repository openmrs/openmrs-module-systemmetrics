package org.openmrs.module.systemmetrics.api.collectors;


import org.openmrs.api.context.Context;
import org.openmrs.module.systemmetrics.PerMinLoginValue;
import org.openmrs.module.systemmetrics.PerMinMetricValue;
import org.openmrs.module.systemmetrics.api.PerformanceMonitoringService;

public class PerMinuteUserLoginsCollectorThread implements Runnable {

    private boolean start;
    private long startTimestamp;
    private long endTimestamp;

    PerformanceMonitoringService performanceMonitoringService;

    public PerMinuteUserLoginsCollectorThread() {

        startPerMinuteUserLoginsCollector();
    }

    @Override
    public void run() {
        Context.openSession();
        performanceMonitoringService = Context.getService(PerformanceMonitoringService.class);
        while (start){
            startTimestamp = System.currentTimeMillis();
            try {
                Thread.sleep(60000*5);
            } catch (InterruptedException e) {
                /* ignore*/
            }
            endTimestamp = System.currentTimeMillis();
            PerMinLoginValue perMinLoginValue = performanceMonitoringService.getAverageLoginValue(startTimestamp,endTimestamp);
            if(perMinLoginValue != null){
                performanceMonitoringService.addPerMinLoginValue(perMinLoginValue);
            }
        }
    }

    public void startPerMinuteUserLoginsCollector(){
        start = true;
    }

    public void stopPerMinuteUserLoginsCollectorr(){
        start = false;
    }
}
