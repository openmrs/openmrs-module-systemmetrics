package org.openmrs.module.systemmetrics.api.db;


import org.openmrs.module.systemmetrics.SavedForm;

public interface SavedFormDAO {

    public int getMetricId();

    public SavedForm addSavedForm(SavedForm savedForm);

    public void removeSavedForm(SavedForm savedForm);

    public void removeSavedFormsWithinTime(long startTimestamp, long endTimestamp);
}
