package org.openmrs.module.systemmetrics.api.db;


import org.openmrs.module.systemmetrics.SavedObservation;

public interface SavedObservationDAO {

    public int getMetricId();

    public SavedObservation addSavedObservation(SavedObservation savedObservation);

    public void removeSavedObservation(SavedObservation savedObservation);

    public void removeSavedObservationsWithinTime(long startTimestamp, long endTimestamp);
}
