package org.openmrs.module.systemmetrics.api.db.hibernate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.openmrs.module.systemmetrics.LoginValue;
import org.openmrs.module.systemmetrics.SavedObservation;
import org.openmrs.module.systemmetrics.api.db.SavedObservationDAO;

import java.util.List;


public class HibernateSavedObservationDAO implements SavedObservationDAO {

    private static Log log = LogFactory.getLog(HibernateSavedObservationDAO.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public int getMetricId() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public SavedObservation addSavedObservation(SavedObservation savedObservation) {
        sessionFactory.getCurrentSession().saveOrUpdate(savedObservation);
        return savedObservation;
    }

    @Override
    public void removeSavedObservation(SavedObservation savedObservation) {
        sessionFactory.getCurrentSession().delete(savedObservation);
    }

    @Override
    public void removeSavedObservationsWithinTime(long startTimestamp, long endTimestamp) {
        Query query =  sessionFactory.getCurrentSession().createQuery("from SavedObservation where timestamp > :startTimestamp and timestamp < :endTimestamp").setParameter("startTimestamp", startTimestamp).setParameter("endTimestamp", endTimestamp);
        List<SavedObservation> valueList = query.list();
        for(SavedObservation savedObservation :valueList )  {
            removeSavedObservation(savedObservation);
        }
    }
}
