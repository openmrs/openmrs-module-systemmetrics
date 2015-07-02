package org.openmrs.module.systemmetrics.api.advisors;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.systemmetrics.SavedEncounter;
import org.openmrs.module.systemmetrics.SavedForm;
import org.openmrs.module.systemmetrics.SavedPatient;
import org.openmrs.module.systemmetrics.api.PerformanceMonitoringService;
import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

import javax.swing.JOptionPane;

public class FieldCreationAfterAdvice implements AfterReturningAdvice {
	
	private final String SAVE_ENCOUNTER = "saveEncounter";
	private final String SAVE_PATIENT = "savePatient";
	private final String SAVE_FORM = "saveForm";
	
    private Log log = LogFactory.getLog(FieldCreationAfterAdvice.class);
    PerformanceMonitoringService performanceMonitoringService
        = Context.getService(PerformanceMonitoringService.class);

    @Override
    public void afterReturning(Object o, Method method, Object[] objects, Object o2) throws Throwable {
        //JOptionPane.showMessageDialog(null, "Method Name : "+method.getName());
    	if (method.getName().equals(SAVE_ENCOUNTER))  {
        	SavedEncounter savedEncounter = new SavedEncounter(System.currentTimeMillis(),5);
            performanceMonitoringService.addSavedEncounter(savedEncounter);
            if(log.isDebugEnabled()){
                log.debug("Adding savedEncounter entry : " + savedEncounter.getTimestamp());
            }
       }
       else if (method.getName().equals(SAVE_FORM))  { 
    	   SavedForm savedForm = new SavedForm(System.currentTimeMillis(),5);
            performanceMonitoringService.addSavedForm(savedForm);
            if(log.isDebugEnabled()){
                log.debug("Adding savedForm entry : " + savedForm.getTimestamp());
            }
       }
       else if (method.getName().equals(SAVE_PATIENT))  {
           SavedPatient savedPatient = new SavedPatient(System.currentTimeMillis(),5);
           performanceMonitoringService.addSavedPatient(savedPatient);
           if(log.isDebugEnabled()){
               log.debug("Adding savedPatient entry : " + savedPatient.getTimestamp());
           }
      }
    }
}
