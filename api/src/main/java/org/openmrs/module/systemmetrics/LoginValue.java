package org.openmrs.module.systemmetrics;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "systemmetrics_login_value")
public class LoginValue {

    @Id
    @Column(name = "timestamp")
    private long timestamp;

    @Column(name = "metric_id")
    private int metricId;

    @Column(name = "login_count")
    private int login_count;

    public LoginValue() {
    }

    public LoginValue(long timestamp, int metricId, int login_count) {
        this.timestamp = timestamp;
        this.metricId = metricId;
        this.login_count = login_count;
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

    public int getLogin_count() {
        return login_count;
    }

    public void setLogin_count(int login_count) {
        this.login_count = login_count;
    }
}

