package org.openmrs.module.systemmetrics.api.collectors;


import org.openmrs.api.context.Context;
import org.openmrs.module.systemmetrics.MetricValue;
import org.openmrs.module.systemmetrics.api.PerformanceMonitoringService;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;

public class UsedMemoryCollectorThread implements Runnable{

    private boolean start;
    private MemoryMXBean memoryBean;
    private MemoryUsage memoryUsage;
    private MetricValue usedMemoryValue;

    PerformanceMonitoringService performanceMonitoringService;

    public UsedMemoryCollectorThread() {
//        Context.openSession();
        startUsedMemoryCollector();
//        performanceMonitoringService = Context.getService(PerformanceMonitoringService.class);
        memoryBean = ManagementFactory.getMemoryMXBean();
    }


    @Override
    public void run() {
        Context.openSession();
        performanceMonitoringService = Context.getService(PerformanceMonitoringService.class);
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
