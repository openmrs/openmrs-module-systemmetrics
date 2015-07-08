package org.openmrs.module.systemmetrics.api.db;

import org.openmrs.module.systemmetrics.ConceptsPerHourEntry;

import java.text.ParseException;
import java.util.List;

public interface ConceptsPerHourEntryDAO {

    public ConceptsPerHourEntry addConceptsPerHourEntry(ConceptsPerHourEntry conceptsPerHourEntry);

    public List<ConceptsPerHourEntry> getConceptsPerHourEntryForChart(long startTimestamp, long endTimestamp);

    public int getCreatedConcepts(long startTimestamp, long endTimestamp);
}
