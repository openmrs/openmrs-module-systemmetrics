package org.openmrs.module.systemmetrics.api.db;


import org.openmrs.module.systemmetrics.MetricType;

import java.util.List;

public interface MetricTypeDAO {

    public int getMetricId();

    public String getMetricName();

    public String getMetricType();

    public MetricType addMetricType(MetricType metricType);

    public void removeMetricType( MetricType metricType);

    public List<MetricType> getAllMetricTypes();

}
