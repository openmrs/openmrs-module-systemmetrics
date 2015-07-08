package org.openmrs.module.systemmetrics.api.db;

import org.openmrs.module.systemmetrics.ObservationsPerHourEntry;

import java.text.ParseException;
import java.util.List;

public interface ObservationsPerHourEntryDAO {

    public ObservationsPerHourEntry addObservationsPerHourEntry(ObservationsPerHourEntry observationsPerHourEntry);

    public List<ObservationsPerHourEntry> getObservationsPerHourEntryForChart(long startTimestamp, long endTimestamp);

    public int getCreatedObservations(long startTimestamp, long endTimestamp);
}
