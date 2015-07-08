package org.openmrs.module.systemmetrics.api.db;

import org.openmrs.module.systemmetrics.ReportsPerHourEntry;

import java.text.ParseException;
import java.util.List;

public interface ReportsPerHourEntryDAO {

    public ReportsPerHourEntry addReportsPerHourEntry(ReportsPerHourEntry reportsPerHourEntry);

    public List<ReportsPerHourEntry> getReportsPerHourEntryForChart(long startTimestamp, long endTimestamp);

    public int getCreatedReports(long startTimestamp, long endTimestamp);
}
