package org.openmrs.module.systemmetrics;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "systemmetrics_metric_type")
public class MetricType implements Serializable {

    @Id
    @Column(name = "metric_id")
    private int metricId;

    @Id
    @Column(name = "metric_name", length = 255)
    private String metricName;

    @Id
    @Column(name = "metric_type", length = 255)
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
