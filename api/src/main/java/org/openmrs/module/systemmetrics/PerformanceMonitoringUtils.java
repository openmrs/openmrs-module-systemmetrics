package org.openmrs.module.systemmetrics;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.systemmetrics.api.PerformanceMonitoringService;
import org.openmrs.module.systemmetrics.api.collectors.UsedMemoryCollectorThread;

import java.util.ArrayList;
import java.util.List;

public class PerformanceMonitoringUtils {

    public static Log log = LogFactory.getLog(PerformanceMonitoringUtils.class);

    public static UsedMemoryCollectorThread usedMemoryCollectorThread ;
    /**
     * Returns the PerformanceMonitoring service from the Context
     *
     * @return PerformanceMonitoring service
     */
    public static PerformanceMonitoringService getService() {
        return Context.getService(PerformanceMonitoringService.class);
    }

    public static void loadCurrentlyAvailableMetircTypes() {
        List<MetricType> currentMetrics = new ArrayList<MetricType>();
        MetricType usedMem = getService().addMetricType(new MetricType(1, "Used Memory", "long"));
        MetricType freeMem = getService().addMetricType(new MetricType(2,"Free Memory", "long"));
        currentMetrics.add(usedMem);
        currentMetrics.add(freeMem);
    }

    public static void startInitialProcesses(){

        loadCurrentlyAvailableMetircTypes();
        usedMemoryCollectorThread = new UsedMemoryCollectorThread();
        Thread collectorThread = new Thread(usedMemoryCollectorThread);
        collectorThread.start();
    }
}
