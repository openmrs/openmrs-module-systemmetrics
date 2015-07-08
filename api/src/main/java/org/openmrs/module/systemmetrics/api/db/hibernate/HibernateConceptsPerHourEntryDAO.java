package org.openmrs.module.systemmetrics.api.db.hibernate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.openmrs.module.systemmetrics.ConceptsPerHourEntry;
import org.openmrs.module.systemmetrics.api.db.ConceptsPerHourEntryDAO;
import java.util.List;


public class HibernateConceptsPerHourEntryDAO implements ConceptsPerHourEntryDAO{

    private static Log log = LogFactory.getLog(HibernateConceptsPerHourEntryDAO.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public ConceptsPerHourEntry addConceptsPerHourEntry(ConceptsPerHourEntry conceptsPerHourEntry) {
        sessionFactory.getCurrentSession().saveOrUpdate(conceptsPerHourEntry);
        return conceptsPerHourEntry;
    }

    @Override
    public List<ConceptsPerHourEntry> getConceptsPerHourEntryForChart(long startTimestamp, long endTimestamp) {
        Query query =  sessionFactory.getCurrentSession().createQuery("from ConceptsPerHourEntry where timestamp > :startTimestamp and timestamp < :endTimestamp").setParameter("startTimestamp", startTimestamp).setParameter("endTimestamp", endTimestamp);
        return query.list();
    }

    @Override
    public int getCreatedConcepts(long startTimestamp, long endTimestamp)  {
        Query query =  sessionFactory.getCurrentSession().createQuery("from SavedConcept where timestamp > :startTimestamp and timestamp < :endTimestamp").setParameter("startTimestamp", startTimestamp).setParameter("endTimestamp", endTimestamp);
        int conceptCount = query.list().size();
        return conceptCount;

    }
}
