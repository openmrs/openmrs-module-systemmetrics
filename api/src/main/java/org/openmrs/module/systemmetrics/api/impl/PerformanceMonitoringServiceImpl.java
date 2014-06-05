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
import org.openmrs.module.systemmetrics.MetricType;
import org.openmrs.module.systemmetrics.api.PerformanceMonitoringService;
import org.openmrs.module.systemmetrics.api.db.MetricTypeDAO;
import org.openmrs.module.systemmetrics.api.db.PerformanceMonitoringDAO;
import org.openmrs.module.systemmetrics.api.db.hibernate.HibernateMetricTypeDAO;

import java.util.List;

/**
 * It is a default implementation of {@link PerformanceMonitoringService}.
 */
public class PerformanceMonitoringServiceImpl extends BaseOpenmrsService implements PerformanceMonitoringService {
	
	protected final Log log = LogFactory.getLog(this.getClass());
	
	private PerformanceMonitoringDAO performanceMonitoringDAO;
    private MetricTypeDAO metricTypeDAO;

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

    public MetricType addMetricType(MetricType metricType) {
        return metricTypeDAO.addMetricType(metricType);
    }

    public void removeMetricType(MetricType metricType) {
        metricTypeDAO.removeMetricType(metricType);
    }

    public List<MetricType> getAllMetricTypes() {
       return metricTypeDAO.getAllMetricTypes();
    }
}