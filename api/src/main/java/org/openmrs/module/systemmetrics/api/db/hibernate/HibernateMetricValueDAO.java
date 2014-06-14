package org.openmrs.module.systemmetrics.api.db.hibernate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.openmrs.module.systemmetrics.MetricValue;
import org.openmrs.module.systemmetrics.api.db.MetricValueDAO;

public class HibernateMetricValueDAO implements MetricValueDAO {

    private static Log log = LogFactory.getLog(HibernateMetricValueDAO.class);

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
    public MetricValue addMetricValue(MetricValue metricValue) {
        sessionFactory.getCurrentSession().saveOrUpdate(metricValue);
        return metricValue;
    }

    @Override
    public void removeMetricValue(MetricValue metricValue) {
        sessionFactory.getCurrentSession().delete(metricValue);
    }


}
