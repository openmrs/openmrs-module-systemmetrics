package org.openmrs.module.systemmetrics;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "systemmetrics_saved_patient")
public class SavedPatient {

    @Id
    @Column(name = "timestamp")
    private long timestamp;

    @Column(name = "metric_id")
    private int metricId;

    public SavedPatient() {
    }

    public SavedPatient(long timestamp, int metricId) {
        this.timestamp = timestamp;
        this.metricId = metricId;
    }

    public int getMetricId() {
        return metricId;
    }

    public void setMetricId(int metricId) {
        this.metricId = metricId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }


}
