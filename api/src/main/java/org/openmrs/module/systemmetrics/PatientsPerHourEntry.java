package org.openmrs.module.systemmetrics;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "systemmetrics_patients_per_hour")

public class PatientsPerHourEntry {

    @Id
    @Column(name = "timestamp")
    private long timestamp;

    @Column(name = "metric_id")
    private int metricId;

    @Column(name = "patient_count")
    private long patientCount;

    public PatientsPerHourEntry() {
    }

    public PatientsPerHourEntry(long timestamp, int metricId, long patientCount) {
        this.timestamp = timestamp;
        this.metricId = metricId;
        this.patientCount = patientCount;
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

    public long getPatientCount() {
        return patientCount;
    }

    public void setPatientCount(long patientCount) {
        this.patientCount = patientCount;
    }
}
