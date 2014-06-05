package org.openmrs.module.systemmetrics;


import javax.persistence.Entity;

@Entity
public class MetricType {

    private int metricId;
    private String metricName;
    private String metricType;

    public MetricType(int metric_id, String metric_name, String metric_type) {
        this.metricId = metric_id;
        this.metricName= metric_name;
        this.metricType=metric_type;
    }

    public int getMetricId() {
        return metricId;
    }

    public void setMetricId(int metric_id) {
        this.metricId = metric_id;
    }

    public String getMetricName() {
        return metricName;
    }

    public void setMetricName(String metric_name) {
        this.metricName = metric_name;
    }

    public String getMetric_type() {
        return metricType;
    }

    public void setMetric_type(String metric_type) {
        this.metricType = metric_type;
    }
}
