package org.openmrs.module.systemmetrics.api.db;

import org.openmrs.module.systemmetrics.FormsPerHourEntry;

import java.text.ParseException;
import java.util.List;

public interface FormsPerHourEntryDAO {

    public FormsPerHourEntry addFormsPerHourEntry(FormsPerHourEntry formsPerHourEntry);

    public List<FormsPerHourEntry> getFormsPerHourEntryForChart(long startTimestamp, long endTimestamp);

    public int getCreatedForms(long startTimestamp, long endTimestamp);
}
