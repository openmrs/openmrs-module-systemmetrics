/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.systemmetrics.api;

import org.openmrs.api.OpenmrsService;
import org.openmrs.module.systemmetrics.*;
import org.openmrs.module.systemmetrics.api.db.EncountersPerHourEntryDAO;
import org.openmrs.module.systemmetrics.api.db.FormsPerHourEntryDAO;
import org.openmrs.module.systemmetrics.api.db.PatientsPerHourEntryDAO;
import org.openmrs.module.systemmetrics.api.db.SavedEncounterDAO;
import org.openmrs.module.systemmetrics.api.db.SavedFormDAO;
import org.openmrs.module.systemmetrics.api.db.SavedPatientDAO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * This service exposes module's core functionality. It is a Spring managed bean which is configured in moduleApplicationContext.xml.
 * <p>
 * It can be accessed only via Context:<br>
 * <code>
 * Context.getService(PerformanceMonitoringService.class).someMethod();
 * </code>
 * 
 * @see org.openmrs.api.context.Context
 */
@Transactional
public interface PerformanceMonitoringService extends OpenmrsService {

    public MetricType addMetricType(MetricType metricType);

    public void removeMetricType(MetricType metricType);

    public List<MetricType> getAllMetricTypes();

    public MetricValue addMetricValue(MetricValue metricValue);

    public void removeMetricVale(MetricValue metricValue);

    public PerMinMetricValue getMetricValue(long startTimestamp, long endTimestamp);

    public PerMinMetricValue addPerMinMetricValue (PerMinMetricValue perMinMetricValue);

    public List<MetricValue> getMetricValuesForChart(long startTimestamp, long endTimestamp);

    public List<PerMinMetricValue> getPerMinMetricValuesForChart(long startTimestamp, long endTimestamp);

    public void removeMetricValuesInPreviousMins(long startTimestamp, long endTimestamp);

    public void removePerminuteMetricValuesInPreviousHours(long startTimestamp, long endTimestamp);

    public LoginValue addLoginValue(LoginValue loginValue);

    public void removeLoginValue(LoginValue loginValue);

    public List<LoginValue> getLoginValuesForChart(long startTimestamp, long endTimestamp);

    public void removeLoginValuesWithinTime(long startTimestamp, long endTimestamp);

    public PerMinLoginValue getAverageLoginValue(long startTimestamp, long endTimestamp);

    public void addPerMinLoginValue(PerMinLoginValue perMinLoginValue);

    public List<PerMinLoginValue> getPerMinLoginValuesForChart(long startTimestamp, long endTimestamp);

    public FormsPerHourEntry addFormsPerHourEntry(FormsPerHourEntry formsPerHourEntry);

    public List<FormsPerHourEntry> getFormsPerHourEntryForChart(long startTimestamp, long endTimestamp);

    public int getCreatedForms(long startTimestamp, long endTimestamp);

	public SavedForm addSavedForm(SavedForm savedForm);
	
	public void removeSavedForm(SavedForm savedForm);

	public void removeSavedFormsWithinTime(long startTimestamp, long endTimestamp);
	
    public FormsPerHourEntryDAO getFormsPerHourEntryDAO();

    public void setFormsPerHourEntryDAO(FormsPerHourEntryDAO formsPerHourEntryDAO);
    
    public SavedFormDAO getSavedFormDAO();
    
    public void setSavedFormDAO(SavedFormDAO savedFormDAO); 
    
    public SavedEncounter addSavedEncounter(SavedEncounter savedEncounter);

    public void removeSavedEncounter(SavedEncounter savedEncounter);

    public void removeSavedEncountersWithinTime(long startTimestamp, long endTimestamp);

    public EncountersPerHourEntry addEncountersPerHourEntry(EncountersPerHourEntry encountersPerHourEntry);

    public List<EncountersPerHourEntry> getEncountersPerHourEntryForChart(long startTimestamp, long endTimestamp);

    public int getCreatedEncounters(long startTimestamp, long endTimestamp);

    public SavedEncounterDAO getSavedEncounterDAO();

    public void setSavedEncounterDAO(SavedEncounterDAO savedEncounterDAO);
	
    public EncountersPerHourEntryDAO getEncountersPerHourEntryDAO();

    public void setEncountersPerHourEntryDAO(EncountersPerHourEntryDAO encountersPerHourEntryDAO);

	public SavedPatient addSavedPatient(SavedPatient savedPatient);

	public void removeSavedPatient(SavedPatient savedPatient);

	public void removeSavedPatientsWithinTime(long startTimestamp, long endTimestamp);

    public PatientsPerHourEntry addPatientsPerHourEntry(PatientsPerHourEntry patientsPerHourEntry);

    public List<PatientsPerHourEntry> getPatientsPerHourEntryForChart(long startTimestamp, long endTimestamp);

    public int getCreatedPatients(long startTimestamp, long endTimestamp);

    public SavedPatientDAO getSavedPatientDAO();
	
    public void setSavedPatientDAO(SavedPatientDAO savedPatientDAO);
    
    public PatientsPerHourEntryDAO getPatientsPerHourEntryDAO();

    public void setPatientsPerHourEntryDAO(PatientsPerHourEntryDAO patientsPerHourEntryDAO);
}