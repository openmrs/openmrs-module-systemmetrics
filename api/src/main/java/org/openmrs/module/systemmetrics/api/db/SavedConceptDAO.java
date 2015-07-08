package org.openmrs.module.systemmetrics.api.db;


import org.openmrs.module.systemmetrics.SavedConcept;

public interface SavedConceptDAO {

    public int getMetricId();

    public SavedConcept addSavedConcept(SavedConcept savedConcept);

    public void removeSavedConcept(SavedConcept savedConcept);

    public void removeSavedConceptsWithinTime(long startTimestamp, long endTimestamp);
}
