package org.openmrs.module.systemmetrics.api.advisors;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.systemmetrics.SavedEncounter;
import org.openmrs.module.systemmetrics.api.PerformanceMonitoringService;
import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

public class EncounterCreationAfterAdvice implements AfterReturningAdvice {

    private Log log = LogFactory.getLog(EncounterCreationAfterAdvice.class);
    PerformanceMonitoringService performanceMonitoringService
        = Context.getService(PerformanceMonitoringService.class);

    @Override
    public void afterReturning(Object o, Method method, Object[] objects, Object o2) throws Throwable {
        if (method.getName().equals("saveEncounter"))  {
            SavedEncounter savedEncounter = new SavedEncounter(System.currentTimeMillis(),5);
            performanceMonitoringService.addSavedEncounter(savedEncounter);
            if(log.isDebugEnabled()){
                log.debug("Adding savedEncounter entry : " + savedEncounter.getTimestamp());
            }
       }
    }
}
