package org.openmrs.module.systemmetrics;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "systemmetrics_forms_per_hour")

public class FormsPerHourEntry {

    @Id
    @Column(name = "timestamp")
    private long timestamp;

    @Column(name = "metric_id")
    private int metricId;

    @Column(name = "form_count")
    private long formCount;

    public FormsPerHourEntry() {
    }

    public FormsPerHourEntry(long timestamp, int metricId, long formCount) {
        this.timestamp = timestamp;
        this.metricId = metricId;
        this.formCount = formCount;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getMetricId() {
        return metricId;
    }

    public void setMetricId(int metricId) {
        this.metricId = metricId;
    }

    public long getFormCount() {
        return formCount;
    }

    public void setFormCount(long formCount) {
        this.formCount = formCount;
    }
}
