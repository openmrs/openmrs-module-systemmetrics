package org.openmrs.module.systemmetrics.api.db.hibernate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.openmrs.module.systemmetrics.PatientsPerHourEntry;
import org.openmrs.module.systemmetrics.api.db.PatientsPerHourEntryDAO;

import java.util.List;


public class HibernatePatientsPerHourEntryDAO implements PatientsPerHourEntryDAO{

    private static Log log = LogFactory.getLog(HibernatePatientsPerHourEntryDAO.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public PatientsPerHourEntry addPatientsPerHourEntry(PatientsPerHourEntry patientsPerHourEntry) {
        sessionFactory.getCurrentSession().saveOrUpdate(patientsPerHourEntry);
        return patientsPerHourEntry;
    }

    @Override
    public List<PatientsPerHourEntry> getPatientsPerHourEntryForChart(long startTimestamp, long endTimestamp) {
        Query query =  sessionFactory.getCurrentSession().createQuery("from PatientsPerHourEntry where timestamp > :startTimestamp and timestamp < :endTimestamp").setParameter("startTimestamp", startTimestamp).setParameter("endTimestamp", endTimestamp);
        return query.list();
    }

    @Override
    public int getCreatedPatients(long startTimestamp, long endTimestamp)  {
        Query query =  sessionFactory.getCurrentSession().createQuery("from SavedPatient where timestamp > :startTimestamp and timestamp < :endTimestamp").setParameter("startTimestamp", startTimestamp).setParameter("endTimestamp", endTimestamp);
        int patientCount = query.list().size();
        return patientCount;

    }
}
