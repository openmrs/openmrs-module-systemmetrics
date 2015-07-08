package org.openmrs.module.systemmetrics;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "systemmetrics_reports_per_hour")

public class ReportsPerHourEntry {

    @Id
    @Column(name = "timestamp")
    private long timestamp;

    @Column(name = "metric_id")
    private int metricId;

    @Column(name = "report_count")
    private long reportCount;

    public ReportsPerHourEntry() {
    }

    public ReportsPerHourEntry(long timestamp, int metricId, long reportCount) {
        this.timestamp = timestamp;
        this.metricId = metricId;
        this.reportCount = reportCount;
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

    public long getReportCount() {
        return reportCount;
    }

    public void setFormCount(long reportCount) {
        this.reportCount = reportCount;
    }
}
