package org.openmrs.module.systemmetrics.api.db.hibernate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.openmrs.module.systemmetrics.VisitsPerHourEntry;
import org.openmrs.module.systemmetrics.api.db.VisitsPerHourEntryDAO;
import java.util.List;


public class HibernateVisitsPerHourEntryDAO implements VisitsPerHourEntryDAO{

    private static Log log = LogFactory.getLog(HibernateVisitsPerHourEntryDAO.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public VisitsPerHourEntry addVisitsPerHourEntry(VisitsPerHourEntry visitsPerHourEntry) {
        sessionFactory.getCurrentSession().saveOrUpdate(visitsPerHourEntry);
        return visitsPerHourEntry;
    }

    @Override
    public List<VisitsPerHourEntry> getVisitsPerHourEntryForChart(long startTimestamp, long endTimestamp) {
        Query query =  sessionFactory.getCurrentSession().createQuery("from VisitsPerHourEntry where timestamp > :startTimestamp and timestamp < :endTimestamp").setParameter("startTimestamp", startTimestamp).setParameter("endTimestamp", endTimestamp);
        return query.list();
    }

    @Override
    public int getCreatedVisits(long startTimestamp, long endTimestamp)  {
        Query query =  sessionFactory.getCurrentSession().createQuery("from SavedVisit where timestamp > :startTimestamp and timestamp < :endTimestamp").setParameter("startTimestamp", startTimestamp).setParameter("endTimestamp", endTimestamp);
        int visitCount = query.list().size();
        return visitCount;

    }
}
