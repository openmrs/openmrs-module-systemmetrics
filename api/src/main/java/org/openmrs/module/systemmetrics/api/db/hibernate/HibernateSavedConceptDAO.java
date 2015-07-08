package org.openmrs.module.systemmetrics.api.db.hibernate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.openmrs.module.systemmetrics.LoginValue;
import org.openmrs.module.systemmetrics.SavedConcept;
import org.openmrs.module.systemmetrics.api.db.SavedConceptDAO;

import java.util.List;


public class HibernateSavedConceptDAO implements SavedConceptDAO {

    private static Log log = LogFactory.getLog(HibernateSavedConceptDAO.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public int getMetricId() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public SavedConcept addSavedConcept(SavedConcept savedConcept) {
        sessionFactory.getCurrentSession().saveOrUpdate(savedConcept);
        return savedConcept;
    }

    @Override
    public void removeSavedConcept(SavedConcept savedConcept) {
        sessionFactory.getCurrentSession().delete(savedConcept);
    }

    @Override
    public void removeSavedConceptsWithinTime(long startTimestamp, long endTimestamp) {
        Query query =  sessionFactory.getCurrentSession().createQuery("from SavedConcept where timestamp > :startTimestamp and timestamp < :endTimestamp").setParameter("startTimestamp", startTimestamp).setParameter("endTimestamp", endTimestamp);
        List<SavedConcept> valueList = query.list();
        for(SavedConcept savedConcept :valueList )  {
            removeSavedConcept(savedConcept);
        }
    }
}
