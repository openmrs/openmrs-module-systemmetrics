package org.openmrs.module.systemmetrics.api.db.hibernate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.openmrs.module.systemmetrics.LoginValue;
import org.openmrs.module.systemmetrics.RanReport;
import org.openmrs.module.systemmetrics.api.db.RanReportDAO;

import java.util.List;


public class HibernateRanReportDAO implements RanReportDAO {

    private static Log log = LogFactory.getLog(HibernateRanReportDAO.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public int getMetricId() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public RanReport addRanReport(RanReport ranReport) {
        sessionFactory.getCurrentSession().saveOrUpdate(ranReport);
        return ranReport;
    }

    @Override
    public void removeRanReport(RanReport ranReport) {
        sessionFactory.getCurrentSession().delete(ranReport);
    }

    @Override
    public void removeRanReportsWithinTime(long startTimestamp, long endTimestamp) {
        Query query =  sessionFactory.getCurrentSession().createQuery("from RanReport where timestamp > :startTimestamp and timestamp < :endTimestamp").setParameter("startTimestamp", startTimestamp).setParameter("endTimestamp", endTimestamp);
        List<RanReport> valueList = query.list();
        for(RanReport ranReport :valueList )  {
            removeRanReport(ranReport);
        }
    }
}
