package org.openmrs.module.systemmetrics;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "systemmetrics_concepts_per_hour")

public class ConceptsPerHourEntry {

    @Id
    @Column(name = "timestamp")
    private long timestamp;

    @Column(name = "metric_id")
    private int metricId;

    @Column(name = "concept_count")
    private long conceptCount;

    public ConceptsPerHourEntry() {
    }

    public ConceptsPerHourEntry(long timestamp, int metricId, long conceptCount) {
        this.timestamp = timestamp;
        this.metricId = metricId;
        this.conceptCount = conceptCount;
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

    public long getConceptCount() {
        return conceptCount;
    }

    public void setConceptCount(long conceptCount) {
        this.conceptCount = conceptCount;
    }
}
