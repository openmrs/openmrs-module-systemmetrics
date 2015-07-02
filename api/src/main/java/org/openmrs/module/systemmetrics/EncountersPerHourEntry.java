package org.openmrs.module.systemmetrics;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "systemmetrics_encounters_per_hour")

public class EncountersPerHourEntry {

    @Id
    @Column(name = "timestamp")
    private long timestamp;

    @Column(name = "metric_id")
    private int metricId;

    @Column(name = "encounter_count")
    private long encounterCount;

    public EncountersPerHourEntry() {
    }

    public EncountersPerHourEntry(long timestamp, int metricId, long encounterCount) {
        this.timestamp = timestamp;
        this.metricId = metricId;
        this.encounterCount = encounterCount;
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

    public long getEncounterCount() {
        return encounterCount;
    }

    public void setEncounterCount(long encounterCount) {
        this.encounterCount = encounterCount;
    }
}
