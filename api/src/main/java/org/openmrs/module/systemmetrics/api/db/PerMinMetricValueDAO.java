package org.openmrs.module.systemmetrics.api.db;

import org.openmrs.module.systemmetrics.MetricValue;
import org.openmrs.module.systemmetrics.api.PerMinMetricValue;

public interface PerMinMetricValueDAO {

    public int getMetricId();

    public String getMetricValue();

    public String getMetricTimestamp();

    public PerMinMetricValue addMetricValue(PerMinMetricValue metricValue);

    public void removeMetricValue(PerMinMetricValue metricValue);
}
