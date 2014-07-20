package org.openmrs.module.systemmetrics.api.db.hibernate;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.openmrs.module.systemmetrics.LoginValue;
import org.openmrs.module.systemmetrics.MetricValue;
import org.openmrs.module.systemmetrics.api.db.LoginValueDAO;

import java.util.List;

public class HibernateLoginValueDAO implements LoginValueDAO{

    private static Log log = LogFactory.getLog(HibernateLoginValueDAO.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public int getMetricId() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getLoginValue() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getMetricTimestamp() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public LoginValue addLoginValue(LoginValue loginValue) {
        sessionFactory.getCurrentSession().saveOrUpdate(loginValue);
        return loginValue;
    }

    @Override
    public void removeLoginValue(LoginValue loginValue) {
        sessionFactory.getCurrentSession().delete(loginValue);
    }

    @Override
    public List<LoginValue> getLoginValuesForChart(long startTimestamp, long endTimestamp) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void removeLoginValuesWithinTime(long startTimestamp, long endTimestamp) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
