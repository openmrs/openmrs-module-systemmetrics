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
package org.openmrs.module.systemmetrics.api.db.hibernate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.openmrs.module.systemmetrics.MetricType;
import org.openmrs.module.systemmetrics.api.db.MetricTypeDAO;

import java.util.List;

/**
 * Hibernate implementation of the Data Access Object
 */
public class HibernateMetricTypeDAO implements MetricTypeDAO{


    private static Log log = LogFactory.getLog(HibernateMetricTypeDAO.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public int getMetricId() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getMetricName() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getMetricType() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public MetricType addMetricType(MetricType metricType) {
        sessionFactory.getCurrentSession().saveOrUpdate(metricType);
        return metricType;
    }

    @Override
    public void removeMetricType(MetricType metricType) {
        sessionFactory.getCurrentSession().delete(metricType);
    }

    @Override
    public List<MetricType> getAllMetricTypes() {
        Query query = sessionFactory.getCurrentSession().createQuery("from MetricType order by id asc");
        return (List<MetricType>) query.list();
    }
}
