package org.openmrs.module.systemmetrics.api.db.hibernate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.openmrs.module.systemmetrics.LoginValue;
import org.openmrs.module.systemmetrics.SavedEncounter;
import org.openmrs.module.systemmetrics.api.db.SavedEncounterDAO;

import java.util.List;


public class HibernateSavedEncounterDAO implements SavedEncounterDAO {

    private static Log log = LogFactory.getLog(HibernateSavedEncounterDAO.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public int getMetricId() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public SavedEncounter addSavedEncounter(SavedEncounter savedEncounter) {
        sessionFactory.getCurrentSession().saveOrUpdate(savedEncounter);
        return savedEncounter;
    }

    @Override
    public void removeSavedEncounter(SavedEncounter savedEncounter) {
        sessionFactory.getCurrentSession().delete(savedEncounter);
    }

    @Override
    public void removeSavedEncountersWithinTime(long startTimestamp, long endTimestamp) {
        Query query =  sessionFactory.getCurrentSession().createQuery("from SavedEncounter where timestamp > :startTimestamp and timestamp < :endTimestamp").setParameter("startTimestamp", startTimestamp).setParameter("endTimestamp", endTimestamp);
        List<SavedEncounter> valueList = query.list();
        for(SavedEncounter savedEncounter :valueList )  {
            removeSavedEncounter(savedEncounter);
        }
    }
}
