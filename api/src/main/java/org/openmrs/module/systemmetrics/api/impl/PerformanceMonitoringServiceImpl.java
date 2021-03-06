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
package org.openmrs.module.systemmetrics.api.impl;

import org.openmrs.api.impl.BaseOpenmrsService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.module.systemmetrics.*;
import org.openmrs.module.systemmetrics.api.PerformanceMonitoringService;
import org.openmrs.module.systemmetrics.api.db.*;
import org.openmrs.module.systemmetrics.api.db.hibernate.HibernateMetricTypeDAO;

import java.util.List;

/**
 * It is a default implementation of {@link PerformanceMonitoringService}.
 */
public class PerformanceMonitoringServiceImpl extends BaseOpenmrsService implements PerformanceMonitoringService {
	
	protected final Log log = LogFactory.getLog(this.getClass());
	
	private PerformanceMonitoringDAO performanceMonitoringDAO;
    private MetricTypeDAO metricTypeDAO;
    private MetricValueDAO metricValueDAO;
    private PerMinMetricValueDAO perMinMetricValueDAO;
    private LoginValueDAO loginValueDAO;
    private PerMinLoginValueDAO perMinLoginValueDAO;
    private FormsPerHourEntryDAO formsPerHourEntryDAO;
    private EncountersPerHourEntryDAO encountersPerHourEntryDAO;
    private PatientsPerHourEntryDAO patientsPerHourEntryDAO;
    private ConceptsPerHourEntryDAO conceptsPerHourEntryDAO;
    private VisitsPerHourEntryDAO visitsPerHourEntryDAO;
    private ObservationsPerHourEntryDAO observationsPerHourEntryDAO;
    private ReportsPerHourEntryDAO reportsPerHourEntryDAO;
    private SavedEncounterDAO savedEncounterDAO;
    private SavedPatientDAO savedPatientDAO;
    private SavedFormDAO savedFormDAO;
    private SavedConceptDAO savedConceptDAO;
    private SavedVisitDAO savedVisitDAO;
    private SavedObservationDAO savedObservationDAO;
    private RanReportDAO ranReportDAO;
    
    public void setPerformanceMonitoringDAO(PerformanceMonitoringDAO performanceMonitoringDAO) {
	    this.performanceMonitoringDAO = performanceMonitoringDAO;
    }

    public PerformanceMonitoringDAO getPerformanceMonitoringDAO() {
	    return performanceMonitoringDAO;
    }

    public MetricTypeDAO getMetricTypeDAO() {
        return metricTypeDAO;
    }

    public void setMetricTypeDAO(MetricTypeDAO metricTypeDAO) {
        this.metricTypeDAO = metricTypeDAO;
    }


    public MetricValueDAO getMetricValueDAO() {
        return metricValueDAO;
    }

    public void setMetricValueDAO(MetricValueDAO metricValueDAO) {
        this.metricValueDAO = metricValueDAO;
    }

    public PerMinMetricValueDAO getPerMinMetricValueDAO() {
        return perMinMetricValueDAO;
    }

    public void setPerMinMetricValueDAO(PerMinMetricValueDAO perMinMetricValueDAO) {
        this.perMinMetricValueDAO = perMinMetricValueDAO;
    }

    public LoginValueDAO getLoginValueDAO() {
        return loginValueDAO;
    }

    public void setLoginValueDAO(LoginValueDAO loginValueDAO) {
        this.loginValueDAO = loginValueDAO;
    }

    public PerMinLoginValueDAO getPerMinLoginValueDAO() {
        return perMinLoginValueDAO;
    }

    public void setPerMinLoginValueDAO(PerMinLoginValueDAO perMinLoginValueDAO) {
        this.perMinLoginValueDAO = perMinLoginValueDAO;
    }

    public MetricType addMetricType(MetricType metricType) {
        return metricTypeDAO.addMetricType(metricType);
    }

    public void removeMetricType(MetricType metricType) {
        metricTypeDAO.removeMetricType(metricType);
    }

    public List<MetricType> getAllMetricTypes() {
       return metricTypeDAO.getAllMetricTypes();
    }

    public MetricValue addMetricValue(MetricValue metricValue) {
        return metricValueDAO.addMetricValue(metricValue);
    }

    public void removeMetricVale(MetricValue metricValue) {
        metricValueDAO.removeMetricValue(metricValue);
    }

    @Override
    public PerMinMetricValue getMetricValue(long startTimestamp, long endTimestamp) {
        return metricValueDAO.getMetricValue(startTimestamp,endTimestamp);
    }

    @Override
    public PerMinMetricValue addPerMinMetricValue(PerMinMetricValue perMinMetricValue) {
        return perMinMetricValueDAO.addMetricValue(perMinMetricValue);
    }

    @Override
    public List<MetricValue> getMetricValuesForChart(long startTimestamp, long endTimestamp) {
        return metricValueDAO.getMetricValuesForChart(startTimestamp,endTimestamp);
    }

    @Override
    public void removeMetricValuesInPreviousMins(long startTimestamp, long endTimestamp) {
        metricValueDAO.removeMetricValuesInPreviousMins(startTimestamp, endTimestamp);
    }

    @Override
    public void removePerminuteMetricValuesInPreviousHours(long startTimestamp, long endTimestamp) {
        perMinMetricValueDAO.removePerminuteMetricValuesInPreviousHours(startTimestamp,endTimestamp);
    }

    @Override
    public List<PerMinMetricValue> getPerMinMetricValuesForChart(long startTimestamp, long endTimestamp) {
        return perMinMetricValueDAO.getPerMinMetricValuesForChart(startTimestamp,endTimestamp);
    }

    @Override
    public LoginValue addLoginValue(LoginValue loginValue) {
        return loginValueDAO.addLoginValue(loginValue);
    }

    @Override
    public void removeLoginValue(LoginValue loginValue) {
        loginValueDAO.removeLoginValue(loginValue);
    }

    @Override
    public List<LoginValue> getLoginValuesForChart(long startTimestamp, long endTimestamp) {
        return loginValueDAO.getLoginValuesForChart(startTimestamp,endTimestamp);
    }

    @Override
    public void removeLoginValuesWithinTime(long startTimestamp, long endTimestamp) {
        loginValueDAO.removeLoginValuesWithinTime(startTimestamp,endTimestamp);
    }

    @Override
    public PerMinLoginValue getAverageLoginValue(long startTimestamp, long endTimestamp) {
        return loginValueDAO.getAverageLoginValue(startTimestamp,endTimestamp);
    }

    @Override
    public void addPerMinLoginValue(PerMinLoginValue perMinLoginValue) {
        perMinLoginValueDAO.addPerMinLoginValue(perMinLoginValue);
    }

    @Override
    public List<PerMinLoginValue> getPerMinLoginValuesForChart(long startTimestamp, long endTimestamp) {
        return perMinLoginValueDAO.getPerMinLoginValuesForChart(startTimestamp,endTimestamp);
    }

    public SavedEncounterDAO getSavedEncounterDAO() {
        return savedEncounterDAO;
    }

    public void setSavedEncounterDAO(SavedEncounterDAO savedEncounterDAO) {
        this.savedEncounterDAO = savedEncounterDAO;
    }

    @Override
    public EncountersPerHourEntry addEncountersPerHourEntry(EncountersPerHourEntry encountersPerHourEntry) {
        return encountersPerHourEntryDAO.addEncountersPerHourEntry(encountersPerHourEntry);
    }

    @Override
    public List<EncountersPerHourEntry> getEncountersPerHourEntryForChart(long startTimestamp, long endTimestamp) {
        return encountersPerHourEntryDAO.getEncountersPerHourEntryForChart(startTimestamp,endTimestamp);
    }

    @Override
    public int getCreatedEncounters(long startTimestamp, long endTimestamp){
        return encountersPerHourEntryDAO.getCreatedEncounters(startTimestamp,endTimestamp);
    }
    
    @Override
    public SavedEncounter addSavedEncounter(SavedEncounter savedEncounter) {
        return savedEncounterDAO.addSavedEncounter(savedEncounter);
    }

    @Override
    public void removeSavedEncounter(SavedEncounter savedEncounter) {
        savedEncounterDAO.removeSavedEncounter(savedEncounter);
    }

    @Override
    public void removeSavedEncountersWithinTime(long startTimestamp, long endTimestamp) {
        savedEncounterDAO.removeSavedEncountersWithinTime(startTimestamp,endTimestamp);
    }
    
    public EncountersPerHourEntryDAO getEncountersPerHourEntryDAO() {
        return encountersPerHourEntryDAO;
    }

    public void setEncountersPerHourEntryDAO(EncountersPerHourEntryDAO encountersPerHourEntryDAO) {
        this.encountersPerHourEntryDAO = encountersPerHourEntryDAO;
    }

    public FormsPerHourEntryDAO getFormsPerHourEntryDAO() {
        return formsPerHourEntryDAO;
    }

    public void setFormsPerHourEntryDAO(FormsPerHourEntryDAO formsPerHourEntryDAO) {
        this.formsPerHourEntryDAO = formsPerHourEntryDAO;
    }
    
    @Override
    public FormsPerHourEntry addFormsPerHourEntry(FormsPerHourEntry formsPerHourEntry) {
        return formsPerHourEntryDAO.addFormsPerHourEntry(formsPerHourEntry);
    }

    @Override
    public List<FormsPerHourEntry> getFormsPerHourEntryForChart(long startTimestamp, long endTimestamp) {
        return formsPerHourEntryDAO.getFormsPerHourEntryForChart(startTimestamp,endTimestamp);
    }

    @Override
    public int getCreatedForms(long startTimestamp, long endTimestamp){
        return formsPerHourEntryDAO.getCreatedForms(startTimestamp,endTimestamp);
    }

	@Override
	public SavedForm addSavedForm(SavedForm savedForm) {
		// TODO Auto-generated method stub
		return savedFormDAO.addSavedForm(savedForm);
	}
    @Override
    public void removeSavedForm(SavedForm savedForm) {
        savedFormDAO.removeSavedForm(savedForm);
    }

    @Override
    public void removeSavedFormsWithinTime(long startTimestamp, long endTimestamp) {
        savedFormDAO.removeSavedFormsWithinTime(startTimestamp,endTimestamp);
    }
    
    public SavedFormDAO getSavedFormDAO() {
        return savedFormDAO;
    }

    public void setSavedFormDAO(SavedFormDAO savedFormDAO) {
        this.savedFormDAO = savedFormDAO;
    } 
    
    public SavedPatientDAO getSavedPatientDAO() {
        return savedPatientDAO;
    }

    public void setSavedPatientDAO(SavedPatientDAO savedPatientDAO) {
        this.savedPatientDAO = savedPatientDAO;
    }
    
    @Override
    public PatientsPerHourEntry addPatientsPerHourEntry(PatientsPerHourEntry patientsPerHourEntry) {
        return patientsPerHourEntryDAO.addPatientsPerHourEntry(patientsPerHourEntry);
    }

    @Override
    public List<PatientsPerHourEntry> getPatientsPerHourEntryForChart(long startTimestamp, long endTimestamp) {
        return patientsPerHourEntryDAO.getPatientsPerHourEntryForChart(startTimestamp,endTimestamp);
    }

    @Override
    public int getCreatedPatients(long startTimestamp, long endTimestamp){
        return patientsPerHourEntryDAO.getCreatedPatients(startTimestamp,endTimestamp);
    }

	@Override
	public SavedPatient addSavedPatient(SavedPatient savedPatient) {
		// TODO Auto-generated method stub
		return savedPatientDAO.addSavedPatient(savedPatient);
	}
    @Override
    public void removeSavedPatient(SavedPatient savedPatient) {
        savedPatientDAO.removeSavedPatient(savedPatient);
    }

    @Override
    public void removeSavedPatientsWithinTime(long startTimestamp, long endTimestamp) {
        savedPatientDAO.removeSavedPatientsWithinTime(startTimestamp,endTimestamp);
    }
     
    public PatientsPerHourEntryDAO getPatientsPerHourEntryDAO() {
        return patientsPerHourEntryDAO;
    }

    public void setPatientsPerHourEntryDAO(PatientsPerHourEntryDAO patientsPerHourEntryDAO) {
        this.patientsPerHourEntryDAO = patientsPerHourEntryDAO;
    }
    
    public SavedVisitDAO getSavedVisitDAO() {
        return savedVisitDAO;
    }

    public void setSavedVisitDAO(SavedVisitDAO savedVisitDAO) {
        this.savedVisitDAO = savedVisitDAO;
    }
    
    @Override
    public VisitsPerHourEntry addVisitsPerHourEntry(VisitsPerHourEntry visitsPerHourEntry) {
        return visitsPerHourEntryDAO.addVisitsPerHourEntry(visitsPerHourEntry);
    }

    @Override
    public List<VisitsPerHourEntry> getVisitsPerHourEntryForChart(long startTimestamp, long endTimestamp) {
        return visitsPerHourEntryDAO.getVisitsPerHourEntryForChart(startTimestamp,endTimestamp);
    }

    @Override
    public int getCreatedVisits(long startTimestamp, long endTimestamp){
        return visitsPerHourEntryDAO.getCreatedVisits(startTimestamp,endTimestamp);
    }

	@Override
	public SavedVisit addSavedVisit(SavedVisit savedVisit) {
		// TODO Auto-generated method stub
		return savedVisitDAO.addSavedVisit(savedVisit);
	}
    @Override
    public void removeSavedVisit(SavedVisit savedVisit) {
        savedVisitDAO.removeSavedVisit(savedVisit);
    }

    @Override
    public void removeSavedVisitsWithinTime(long startTimestamp, long endTimestamp) {
        savedVisitDAO.removeSavedVisitsWithinTime(startTimestamp,endTimestamp);
    }
     
    public VisitsPerHourEntryDAO getVisitsPerHourEntryDAO() {
        return visitsPerHourEntryDAO;
    }

    public void setVisitsPerHourEntryDAO(VisitsPerHourEntryDAO visitsPerHourEntryDAO) {
        this.visitsPerHourEntryDAO = visitsPerHourEntryDAO;
    }
    
    public SavedObservationDAO getSavedObservationDAO() {
        return savedObservationDAO;
    }

    public void setSavedObservationDAO(SavedObservationDAO savedObservationDAO) {
        this.savedObservationDAO = savedObservationDAO;
    }
    
    @Override
    public ObservationsPerHourEntry addObservationsPerHourEntry(ObservationsPerHourEntry observationsPerHourEntry) {
        return observationsPerHourEntryDAO.addObservationsPerHourEntry(observationsPerHourEntry);
    }

    @Override
    public List<ObservationsPerHourEntry> getObservationsPerHourEntryForChart(long startTimestamp, long endTimestamp) {
        return observationsPerHourEntryDAO.getObservationsPerHourEntryForChart(startTimestamp,endTimestamp);
    }

    @Override
    public int getCreatedObservations(long startTimestamp, long endTimestamp){
        return observationsPerHourEntryDAO.getCreatedObservations(startTimestamp,endTimestamp);
    }

	@Override
	public SavedObservation addSavedObservation(SavedObservation savedObservation) {
		// TODO Auto-generated method stub
		return savedObservationDAO.addSavedObservation(savedObservation);
	}
    @Override
    public void removeSavedObservation(SavedObservation savedObservation) {
        savedObservationDAO.removeSavedObservation(savedObservation);
    }

    @Override
    public void removeSavedObservationsWithinTime(long startTimestamp, long endTimestamp) {
        savedObservationDAO.removeSavedObservationsWithinTime(startTimestamp,endTimestamp);
    }
     
    public ObservationsPerHourEntryDAO getObservationsPerHourEntryDAO() {
        return observationsPerHourEntryDAO;
    }

    public void setObservationsPerHourEntryDAO(ObservationsPerHourEntryDAO observationsPerHourEntryDAO) {
        this.observationsPerHourEntryDAO = observationsPerHourEntryDAO;
    }
    
    public SavedConceptDAO getSavedConceptDAO() {
        return savedConceptDAO;
    }

    public void setSavedConceptDAO(SavedConceptDAO savedConceptDAO) {
        this.savedConceptDAO = savedConceptDAO;
    }
    
    @Override
    public ConceptsPerHourEntry addConceptsPerHourEntry(ConceptsPerHourEntry conceptsPerHourEntry) {
        return conceptsPerHourEntryDAO.addConceptsPerHourEntry(conceptsPerHourEntry);
    }

    @Override
    public List<ConceptsPerHourEntry> getConceptsPerHourEntryForChart(long startTimestamp, long endTimestamp) {
        return conceptsPerHourEntryDAO.getConceptsPerHourEntryForChart(startTimestamp,endTimestamp);
    }

    @Override
    public int getCreatedConcepts(long startTimestamp, long endTimestamp){
        return conceptsPerHourEntryDAO.getCreatedConcepts(startTimestamp,endTimestamp);
    }

	@Override
	public SavedConcept addSavedConcept(SavedConcept savedConcept) {
		// TODO Auto-generated method stub
		return savedConceptDAO.addSavedConcept(savedConcept);
	}
    @Override
    public void removeSavedConcept(SavedConcept savedConcept) {
        savedConceptDAO.removeSavedConcept(savedConcept);
    }

    @Override
    public void removeSavedConceptsWithinTime(long startTimestamp, long endTimestamp) {
        savedConceptDAO.removeSavedConceptsWithinTime(startTimestamp,endTimestamp);
    }
     
    public ConceptsPerHourEntryDAO getConceptsPerHourEntryDAO() {
        return conceptsPerHourEntryDAO;
    }

    public void setConceptsPerHourEntryDAO(ConceptsPerHourEntryDAO conceptsPerHourEntryDAO) {
        this.conceptsPerHourEntryDAO = conceptsPerHourEntryDAO;
    }
    
    
    public RanReportDAO getRanReportDAO() {
        return ranReportDAO;
    }

    public void setRanReportDAO(RanReportDAO ranReportDAO) {
        this.ranReportDAO = ranReportDAO;
    }
    
    @Override
    public ReportsPerHourEntry addReportsPerHourEntry(ReportsPerHourEntry reportsPerHourEntry) {
        return reportsPerHourEntryDAO.addReportsPerHourEntry(reportsPerHourEntry);
    }

    @Override
    public List<ReportsPerHourEntry> getReportsPerHourEntryForChart(long startTimestamp, long endTimestamp) {
        return reportsPerHourEntryDAO.getReportsPerHourEntryForChart(startTimestamp,endTimestamp);
    }

    @Override
    public int getCreatedReports(long startTimestamp, long endTimestamp){
        return reportsPerHourEntryDAO.getCreatedReports(startTimestamp,endTimestamp);
    }

	@Override
	public RanReport addRanReport(RanReport ranReport) {
		// TODO Auto-generated method stub
		return ranReportDAO.addRanReport(ranReport);
	}
    @Override
    public void removeRanReport(RanReport ranReport) {
        ranReportDAO.removeRanReport(ranReport);
    }

    @Override
    public void removeRanReportsWithinTime(long startTimestamp, long endTimestamp) {
        ranReportDAO.removeRanReportsWithinTime(startTimestamp,endTimestamp);
    }
     
    public ReportsPerHourEntryDAO getReportsPerHourEntryDAO() {
        return reportsPerHourEntryDAO;
    }

    public void setReportsPerHourEntryDAO(ReportsPerHourEntryDAO reportsPerHourEntryDAO) {
        this.reportsPerHourEntryDAO = reportsPerHourEntryDAO;
    }
    
}