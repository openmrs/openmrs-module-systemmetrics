package org.openmrs.module.systemmetrics.api.db.hibernate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.openmrs.module.systemmetrics.LoginValue;
import org.openmrs.module.systemmetrics.SavedPatient;
import org.openmrs.module.systemmetrics.api.db.SavedPatientDAO;

import java.util.List;


public class HibernateSavedPatientDAO implements SavedPatientDAO {

    private static Log log = LogFactory.getLog(HibernateSavedPatientDAO.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public int getMetricId() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public SavedPatient addSavedPatient(SavedPatient savedPatient) {
        sessionFactory.getCurrentSession().saveOrUpdate(savedPatient);
        return savedPatient;
    }

    @Override
    public void removeSavedPatient(SavedPatient savedPatient) {
        sessionFactory.getCurrentSession().delete(savedPatient);
    }

    @Override
    public void removeSavedPatientsWithinTime(long startTimestamp, long endTimestamp) {
        Query query =  sessionFactory.getCurrentSession().createQuery("from SavedPatient where timestamp > :startTimestamp and timestamp < :endTimestamp").setParameter("startTimestamp", startTimestamp).setParameter("endTimestamp", endTimestamp);
        List<SavedPatient> valueList = query.list();
        for(SavedPatient savedPatient :valueList )  {
            removeSavedPatient(savedPatient);
        }
    }
}
