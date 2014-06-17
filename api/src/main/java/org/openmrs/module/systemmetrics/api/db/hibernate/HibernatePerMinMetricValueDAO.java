package org.openmrs.module.systemmetrics.api.db.hibernate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.openmrs.module.systemmetrics.api.PerMinMetricValue;
import org.openmrs.module.systemmetrics.api.db.PerMinMetricValueDAO;


public class HibernatePerMinMetricValueDAO implements PerMinMetricValueDAO {

    private static Log log = LogFactory.getLog(HibernatePerMinMetricValueDAO.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public int getMetricId() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getMetricValue() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getMetricTimestamp() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public PerMinMetricValue addMetricValue(PerMinMetricValue metricValue) {
        sessionFactory.getCurrentSession().saveOrUpdate(metricValue);
        return metricValue;
    }

    @Override
    public void removeMetricValue(PerMinMetricValue metricValue) {
        sessionFactory.getCurrentSession().delete(metricValue);
    }
}
