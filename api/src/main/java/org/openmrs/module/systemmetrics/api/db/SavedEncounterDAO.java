package org.openmrs.module.systemmetrics.api.db;


import org.openmrs.module.systemmetrics.SavedEncounter;

public interface SavedEncounterDAO {

    public int getMetricId();

    public SavedEncounter addSavedEncounter(SavedEncounter savedEncounter);

    public void removeSavedEncounter(SavedEncounter savedEncounter);

    public void removeSavedEncountersWithinTime(long startTimestamp, long endTimestamp);
}
