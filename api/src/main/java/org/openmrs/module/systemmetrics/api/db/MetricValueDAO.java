package org.openmrs.module.systemmetrics.api.db;


import org.openmrs.module.systemmetrics.MetricValue;
import org.openmrs.module.systemmetrics.api.PerMinMetricValue;

import java.util.List;

public interface MetricValueDAO {

    public int getMetricId();

    public String getMetricValue();

    public String getMetricTimestamp();

    public MetricValue addMetricValue(MetricValue metricValue);

    public void removeMetricValue(MetricValue metricValue);

    public PerMinMetricValue getMetricValue(long startTimestamp, long endTimestamp);

    public List<MetricValue> getMetricValuesForChart(long startTimestamp, long endTimestamp);
}
