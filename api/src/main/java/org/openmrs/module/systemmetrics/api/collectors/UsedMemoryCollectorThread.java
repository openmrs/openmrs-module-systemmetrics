package org.openmrs.module.systemmetrics.api.collectors;


import org.openmrs.module.systemmetrics.MetricValue;
import org.openmrs.module.systemmetrics.PerformanceMonitoringUtils;
import org.openmrs.module.systemmetrics.api.PerformanceMonitoringService;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.sql.Timestamp;
import java.util.Date;

public class UsedMemoryCollectorThread implements Runnable{

    private boolean start;
    private MemoryMXBean memoryBean;
    private MemoryUsage memoryUsage;
    private MetricValue usedMemoryValue;

    PerformanceMonitoringService performanceMonitoringService;

    public UsedMemoryCollectorThread() {
        performanceMonitoringService =
                PerformanceMonitoringUtils.getService();
        memoryBean = ManagementFactory.getMemoryMXBean();
    }

    @Override
    public void run() {

        while (start){
           memoryUsage = memoryBean.getHeapMemoryUsage();
           usedMemoryValue = new MetricValue(1,System.currentTimeMillis(), memoryUsage.getUsed());
           System.out.println("Used memory: " + memoryUsage.getUsed() + " and current time: " + System.currentTimeMillis());
           performanceMonitoringService.addMetricValue(usedMemoryValue);
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                /* ignore*/
            }
        }

    }

    private boolean isStarted(){
        return start;
    }

    public void startUsedMemoryCollector(){
        start = true;
    }

    public void stopUsedMemoryCollector(){
        start = false;
    }
}
