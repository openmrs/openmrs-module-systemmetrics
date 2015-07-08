package org.openmrs.module.systemmetrics.api.db.hibernate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.openmrs.module.systemmetrics.ReportsPerHourEntry;
import org.openmrs.module.systemmetrics.api.db.ReportsPerHourEntryDAO;
import java.util.List;


public class HibernateReportsPerHourEntryDAO implements ReportsPerHourEntryDAO{

    private static Log log = LogFactory.getLog(HibernateReportsPerHourEntryDAO.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public ReportsPerHourEntry addReportsPerHourEntry(ReportsPerHourEntry reportsPerHourEntry) {
        sessionFactory.getCurrentSession().saveOrUpdate(reportsPerHourEntry);
        return reportsPerHourEntry;
    }

    @Override
    public List<ReportsPerHourEntry> getReportsPerHourEntryForChart(long startTimestamp, long endTimestamp) {
        Query query =  sessionFactory.getCurrentSession().createQuery("from ReportsPerHourEntry where timestamp > :startTimestamp and timestamp < :endTimestamp").setParameter("startTimestamp", startTimestamp).setParameter("endTimestamp", endTimestamp);
        return query.list();
    }

    @Override
    public int getCreatedReports(long startTimestamp, long endTimestamp)  {
        Query query =  sessionFactory.getCurrentSession().createQuery("from RanReport where timestamp > :startTimestamp and timestamp < :endTimestamp").setParameter("startTimestamp", startTimestamp).setParameter("endTimestamp", endTimestamp);
        int reportCount = query.list().size();
        return reportCount;

    }
}
