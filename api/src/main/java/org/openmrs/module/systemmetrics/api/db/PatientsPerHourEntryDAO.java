package org.openmrs.module.systemmetrics.api.db;

import org.openmrs.module.systemmetrics.PatientsPerHourEntry;

import java.text.ParseException;
import java.util.List;

public interface PatientsPerHourEntryDAO {

    public PatientsPerHourEntry addPatientsPerHourEntry(PatientsPerHourEntry patientsPerHourEntry);

    public List<PatientsPerHourEntry> getPatientsPerHourEntryForChart(long startTimestamp, long endTimestamp);

    public int getCreatedPatients(long startTimestamp, long endTimestamp);
}
