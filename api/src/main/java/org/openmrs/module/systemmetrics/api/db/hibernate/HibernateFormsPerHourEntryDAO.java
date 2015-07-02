package org.openmrs.module.systemmetrics.api.db.hibernate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.openmrs.module.systemmetrics.FormsPerHourEntry;
import org.openmrs.module.systemmetrics.api.db.FormsPerHourEntryDAO;
import java.util.List;


public class HibernateFormsPerHourEntryDAO implements FormsPerHourEntryDAO{

    private static Log log = LogFactory.getLog(HibernateFormsPerHourEntryDAO.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public FormsPerHourEntry addFormsPerHourEntry(FormsPerHourEntry formsPerHourEntry) {
        sessionFactory.getCurrentSession().saveOrUpdate(formsPerHourEntry);
        return formsPerHourEntry;
    }

    @Override
    public List<FormsPerHourEntry> getFormsPerHourEntryForChart(long startTimestamp, long endTimestamp) {
        Query query =  sessionFactory.getCurrentSession().createQuery("from FormsPerHourEntry where timestamp > :startTimestamp and timestamp < :endTimestamp").setParameter("startTimestamp", startTimestamp).setParameter("endTimestamp", endTimestamp);
        return query.list();
    }

    @Override
    public int getCreatedForms(long startTimestamp, long endTimestamp)  {
        Query query =  sessionFactory.getCurrentSession().createQuery("from SavedForm where timestamp > :startTimestamp and timestamp < :endTimestamp").setParameter("startTimestamp", startTimestamp).setParameter("endTimestamp", endTimestamp);
        int formCount = query.list().size();
        return formCount;

    }
}
