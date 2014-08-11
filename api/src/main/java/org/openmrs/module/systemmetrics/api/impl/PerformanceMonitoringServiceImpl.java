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
import org.openmrs.module.systemmetrics.api.db.hibernate.HibernateSavedEncounterDAO;

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
    private SavedEncounterDAO savedEncounterDAO;

    public SavedEncounterDAO getSavedEncounterDAO() {
        return savedEncounterDAO;
    }

    public void setSavedEncounterDAO(SavedEncounterDAO savedEncounterDAO) {
        this.savedEncounterDAO = savedEncounterDAO;
    }

    public FormsPerHourEntryDAO getFormsPerHourEntryDAO() {
        return formsPerHourEntryDAO;
    }

    public void setFormsPerHourEntryDAO(FormsPerHourEntryDAO formsPerHourEntryDAO) {
        this.formsPerHourEntryDAO = formsPerHourEntryDAO;
    }

    public void setPerformanceMonitoringDAO(PerformanceMonitoringDAO performanceMonitoringDAO) {
	    this.performanceMonitoringDAO = performanceMonitoringDAO;
    }

    public PerformanceMonitoringDAO getPerformanceMonitoringDAO() {
	    return performanceMonitoringDAO;
    }

    public MetricTypeDAO getMetricTypeDAO() {
        return metricTypeDAO;
    }

    public void setMetricTypeDAO(HibernateMetricTypeDAO metricTypeDAO) {
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

    @Override
    public FormsPerHourEntry addFormsPerHourEntry(FormsPerHourEntry formsPerHourEntry) {
        return formsPerHourEntryDAO.addFormsPerHourEntry(formsPerHourEntry);
    }

    @Override
    public List<FormsPerHourEntry> getFormsPerHourEntryForChart(long startTimestamp, long endTimestamp) {
        return formsPerHourEntryDAO.getFormsPerHourEntryForChart(startTimestamp,endTimestamp);
    }

    @Override
    public int getCreatedEncounters(long startTimestamp, long endTimestamp){
        return formsPerHourEntryDAO.getCreatedEncounters(startTimestamp,endTimestamp);
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
}