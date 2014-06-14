package org.openmrs.module.systemmetrics.api.db;


import org.openmrs.module.systemmetrics.MetricValue;

public interface MetricValueDAO {

    public int getMetricId();

    public String getMetricName();

    public String getMetricType();

    public MetricValue addMetricValue(MetricValue metricValue);

    public void removeMetricValue(MetricValue metricValue);


}
