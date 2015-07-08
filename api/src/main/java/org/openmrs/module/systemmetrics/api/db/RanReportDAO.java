package org.openmrs.module.systemmetrics.api.db;


import org.openmrs.module.systemmetrics.RanReport;

public interface RanReportDAO {

    public int getMetricId();

    public RanReport addRanReport(RanReport savedReport);

    public void removeRanReport(RanReport savedReport);

    public void removeRanReportsWithinTime(long startTimestamp, long endTimestamp);
}
