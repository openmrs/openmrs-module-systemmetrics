package org.openmrs.module.systemmetrics.api.db.hibernate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.openmrs.module.systemmetrics.ObservationsPerHourEntry;
import org.openmrs.module.systemmetrics.api.db.ObservationsPerHourEntryDAO;
import java.util.List;


public class HibernateObservationsPerHourEntryDAO implements ObservationsPerHourEntryDAO{

    private static Log log = LogFactory.getLog(HibernateObservationsPerHourEntryDAO.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public ObservationsPerHourEntry addObservationsPerHourEntry(ObservationsPerHourEntry observationsPerHourEntry) {
        sessionFactory.getCurrentSession().saveOrUpdate(observationsPerHourEntry);
        return observationsPerHourEntry;
    }

    @Override
    public List<ObservationsPerHourEntry> getObservationsPerHourEntryForChart(long startTimestamp, long endTimestamp) {
        Query query =  sessionFactory.getCurrentSession().createQuery("from ObservationsPerHourEntry where timestamp > :startTimestamp and timestamp < :endTimestamp").setParameter("startTimestamp", startTimestamp).setParameter("endTimestamp", endTimestamp);
        return query.list();
    }

    @Override
    public int getCreatedObservations(long startTimestamp, long endTimestamp)  {
        Query query =  sessionFactory.getCurrentSession().createQuery("from SavedObservation where timestamp > :startTimestamp and timestamp < :endTimestamp").setParameter("startTimestamp", startTimestamp).setParameter("endTimestamp", endTimestamp);
        int observationCount = query.list().size();
        return observationCount;

    }
}
