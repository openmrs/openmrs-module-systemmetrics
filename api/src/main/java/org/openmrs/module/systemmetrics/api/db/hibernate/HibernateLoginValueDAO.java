package org.openmrs.module.systemmetrics.api.db.hibernate;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.openmrs.module.systemmetrics.LoginValue;
import org.openmrs.module.systemmetrics.MetricValue;
import org.openmrs.module.systemmetrics.PerMinMetricValue;
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
    public LoginValue getAverageLoginValue(long startTimestamp, long endTimestamp) {
        Query query =  sessionFactory.getCurrentSession().createQuery("from LoginValue where timestamp > :startTimestamp and timestamp < :endTimestamp").setParameter("startTimestamp", startTimestamp).setParameter("endTimestamp", endTimestamp);
        List<LoginValue> valueList = query.list();
        int averageValue = 0;
        int sumOfVals = 0;
        if(valueList.size() != 0){
            for(LoginValue loginVal :valueList )  {
                sumOfVals += loginVal.getLogin_count();
            }
            averageValue = sumOfVals/valueList.size();

            // todo: change this with new Averageloginvalue object later
            return new LoginValue(endTimestamp,3,averageValue);
        }else {
            return null;
        }

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
        Query query =  sessionFactory.getCurrentSession().createQuery("from LoginValue where timestamp > :startTimestamp and timestamp < :endTimestamp").setParameter("startTimestamp", startTimestamp).setParameter("endTimestamp", endTimestamp);
        return query.list();
    }

    @Override
    public void removeLoginValuesWithinTime(long startTimestamp, long endTimestamp) {
        Query query =  sessionFactory.getCurrentSession().createQuery("from LoginValue where timestamp > :startTimestamp and timestamp < :endTimestamp").setParameter("startTimestamp", startTimestamp).setParameter("endTimestamp", endTimestamp);
        List<LoginValue> valueList = query.list();
        for(LoginValue loginValue :valueList )  {
            removeLoginValue(loginValue);
        }
    }
}
