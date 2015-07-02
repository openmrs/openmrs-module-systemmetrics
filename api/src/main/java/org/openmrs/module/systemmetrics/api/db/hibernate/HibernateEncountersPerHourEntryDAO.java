package org.openmrs.module.systemmetrics.api.db.hibernate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.openmrs.module.systemmetrics.EncountersPerHourEntry;
import org.openmrs.module.systemmetrics.api.db.EncountersPerHourEntryDAO;
import java.util.List;


public class HibernateEncountersPerHourEntryDAO implements EncountersPerHourEntryDAO{

    private static Log log = LogFactory.getLog(HibernateEncountersPerHourEntryDAO.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public EncountersPerHourEntry addEncountersPerHourEntry(EncountersPerHourEntry encountersPerHourEntry) {
        sessionFactory.getCurrentSession().saveOrUpdate(encountersPerHourEntry);
        return encountersPerHourEntry;
    }

    @Override
    public List<EncountersPerHourEntry> getEncountersPerHourEntryForChart(long startTimestamp, long endTimestamp) {
        Query query =  sessionFactory.getCurrentSession().createQuery("from EncountersPerHourEntry where timestamp > :startTimestamp and timestamp < :endTimestamp").setParameter("startTimestamp", startTimestamp).setParameter("endTimestamp", endTimestamp);
        return query.list();
    }

    @Override
    public int getCreatedEncounters(long startTimestamp, long endTimestamp)  {
        Query query =  sessionFactory.getCurrentSession().createQuery("from SavedEncounter where timestamp > :startTimestamp and timestamp < :endTimestamp").setParameter("startTimestamp", startTimestamp).setParameter("endTimestamp", endTimestamp);
        int encounterCount = query.list().size();
        return encounterCount;

    }
}
