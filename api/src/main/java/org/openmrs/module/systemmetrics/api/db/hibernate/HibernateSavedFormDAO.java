package org.openmrs.module.systemmetrics.api.db.hibernate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.openmrs.module.systemmetrics.LoginValue;
import org.openmrs.module.systemmetrics.SavedForm;
import org.openmrs.module.systemmetrics.api.db.SavedFormDAO;

import java.util.List;


public class HibernateSavedFormDAO implements SavedFormDAO {

    private static Log log = LogFactory.getLog(HibernateSavedFormDAO.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public int getMetricId() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public SavedForm addSavedForm(SavedForm savedForm) {
        sessionFactory.getCurrentSession().saveOrUpdate(savedForm);
        return savedForm;
    }

    @Override
    public void removeSavedForm(SavedForm savedForm) {
        sessionFactory.getCurrentSession().delete(savedForm);
    }

    @Override
    public void removeSavedFormsWithinTime(long startTimestamp, long endTimestamp) {
        Query query =  sessionFactory.getCurrentSession().createQuery("from SavedForm where timestamp > :startTimestamp and timestamp < :endTimestamp").setParameter("startTimestamp", startTimestamp).setParameter("endTimestamp", endTimestamp);
        List<SavedForm> valueList = query.list();
        for(SavedForm savedForm :valueList )  {
            removeSavedForm(savedForm);
        }
    }
}
