package org.openmrs.module.systemmetrics.api.db;

import org.openmrs.module.systemmetrics.VisitsPerHourEntry;

import java.text.ParseException;
import java.util.List;

public interface VisitsPerHourEntryDAO {

    public VisitsPerHourEntry addVisitsPerHourEntry(VisitsPerHourEntry visitsPerHourEntry);

    public List<VisitsPerHourEntry> getVisitsPerHourEntryForChart(long startTimestamp, long endTimestamp);

    public int getCreatedVisits(long startTimestamp, long endTimestamp);
}
