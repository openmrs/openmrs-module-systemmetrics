package org.openmrs.module.systemmetrics.api.db.hibernate;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.openmrs.module.systemmetrics.PerMinLoginValue;
import org.openmrs.module.systemmetrics.api.db.PerMinLoginValueDAO;

import java.util.List;


public class HibernatePerMinLoginValueDAO implements PerMinLoginValueDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public int getMetricId() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getPerMinLoginValue() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getMetricTimestamp() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public PerMinLoginValue addPerMinLoginValue(PerMinLoginValue loginValue) {
        sessionFactory.getCurrentSession().saveOrUpdate(loginValue);
        return loginValue;
    }

    @Override
    public void removeLoginValue(PerMinLoginValue loginValue) {
        sessionFactory.getCurrentSession().delete(loginValue);
    }

    @Override
    public List<PerMinLoginValue> getPerMinLoginValuesForChart(long startTimestamp, long endTimestamp) {
        Query query =  sessionFactory.getCurrentSession().createQuery("from PerMinLoginValue where timestamp > :startTimestamp and timestamp < :endTimestamp").setParameter("startTimestamp", startTimestamp).setParameter("endTimestamp", endTimestamp);
        return query.list();
    }

    @Override
    public void removePerMinLoginValuesWithinTime(long startTimestamp, long endTimestamp) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
