package org.openmrs.module.systemmetrics.api.db.hibernate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.openmrs.Encounter;
import org.openmrs.module.systemmetrics.FormsPerHourEntry;
import org.openmrs.module.systemmetrics.LoginValue;
import org.openmrs.module.systemmetrics.PerMinLoginValue;
import org.openmrs.module.systemmetrics.api.db.FormsPerHourEntryDAO;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    public int getCreatedEncounters(long startTimestamp, long endTimestamp)  {
        Query query =  sessionFactory.getCurrentSession().createQuery("from SavedEncounter where timestamp > :startTimestamp and timestamp < :endTimestamp").setParameter("startTimestamp", startTimestamp).setParameter("endTimestamp", endTimestamp);
        int encounterCount = query.list().size();
        return encounterCount;

    }
}
