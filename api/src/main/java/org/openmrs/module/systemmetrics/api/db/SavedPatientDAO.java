package org.openmrs.module.systemmetrics.api.db;


import org.openmrs.module.systemmetrics.SavedPatient;

public interface SavedPatientDAO {

    public int getMetricId();

    public SavedPatient addSavedPatient(SavedPatient savedPatient);

    public void removeSavedPatient(SavedPatient savedPatient);

    public void removeSavedPatientsWithinTime(long startTimestamp, long endTimestamp);
}
