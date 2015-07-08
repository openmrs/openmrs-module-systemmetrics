package org.openmrs.module.systemmetrics.api.db;


import org.openmrs.module.systemmetrics.SavedVisit;

public interface SavedVisitDAO {

    public int getMetricId();

    public SavedVisit addSavedVisit(SavedVisit savedVisit);

    public void removeSavedVisit(SavedVisit savedVisit);

    public void removeSavedVisitsWithinTime(long startTimestamp, long endTimestamp);
}
