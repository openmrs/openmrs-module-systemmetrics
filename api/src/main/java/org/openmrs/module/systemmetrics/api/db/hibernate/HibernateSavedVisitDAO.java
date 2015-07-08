package org.openmrs.module.systemmetrics.api.db.hibernate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.openmrs.module.systemmetrics.LoginValue;
import org.openmrs.module.systemmetrics.SavedVisit;
import org.openmrs.module.systemmetrics.api.db.SavedVisitDAO;

import java.util.List;


public class HibernateSavedVisitDAO implements SavedVisitDAO {

    private static Log log = LogFactory.getLog(HibernateSavedVisitDAO.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public int getMetricId() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public SavedVisit addSavedVisit(SavedVisit savedVisit) {
        sessionFactory.getCurrentSession().saveOrUpdate(savedVisit);
        return savedVisit;
    }

    @Override
    public void removeSavedVisit(SavedVisit savedVisit) {
        sessionFactory.getCurrentSession().delete(savedVisit);
    }

    @Override
    public void removeSavedVisitsWithinTime(long startTimestamp, long endTimestamp) {
        Query query =  sessionFactory.getCurrentSession().createQuery("from SavedVisit where timestamp > :startTimestamp and timestamp < :endTimestamp").setParameter("startTimestamp", startTimestamp).setParameter("endTimestamp", endTimestamp);
        List<SavedVisit> valueList = query.list();
        for(SavedVisit savedVisit :valueList )  {
            removeSavedVisit(savedVisit);
        }
    }
}
