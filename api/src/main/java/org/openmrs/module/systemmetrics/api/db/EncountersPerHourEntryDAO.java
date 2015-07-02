package org.openmrs.module.systemmetrics.api.db;

import org.openmrs.module.systemmetrics.EncountersPerHourEntry;

import java.text.ParseException;
import java.util.List;

public interface EncountersPerHourEntryDAO {

    public EncountersPerHourEntry addEncountersPerHourEntry(EncountersPerHourEntry encountersPerHourEntry);

    public List<EncountersPerHourEntry> getEncountersPerHourEntryForChart(long startTimestamp, long endTimestamp);

    public int getCreatedEncounters(long startTimestamp, long endTimestamp);
}
