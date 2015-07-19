package org.openmrs.module.systemmetrics.api.advisors;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.systemmetrics.RanReport;
import org.openmrs.module.systemmetrics.SavedConcept;
import org.openmrs.module.systemmetrics.SavedEncounter;
import org.openmrs.module.systemmetrics.SavedForm;
import org.openmrs.module.systemmetrics.SavedObservation;
import org.openmrs.module.systemmetrics.SavedPatient;
import org.openmrs.module.systemmetrics.SavedVisit;
import org.openmrs.module.systemmetrics.api.PerformanceMonitoringService;
import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

import javax.swing.JOptionPane;

public class FieldCreationAfterAdvice implements AfterReturningAdvice {
	
	private final String SAVE_ENCOUNTER = "saveEncounter";
	private final String SAVE_PATIENT = "savePatient";
	private final String SAVE_FORM = "saveForm";
	private final String RUN_REPORT = "runReport";
	private final String SAVE_OBSERVATION = "saveObservation";
	private final String SAVE_VISIT = "saveVisit";
	private final String SAVE_CONCEPT = "saveConcept";
	
    private Log log = LogFactory.getLog(FieldCreationAfterAdvice.class);
    PerformanceMonitoringService performanceMonitoringService
        = Context.getService(PerformanceMonitoringService.class);

    @Override
    public void afterReturning(Object o, Method method, Object[] objects, Object o2) throws Throwable {
    	try{
    		log.warn("Method Name : "+method.getName());
    	}
    	catch(Exception e){
    		log.error("Error Getting Method");
    	}
    	if (method.getName().equals(SAVE_ENCOUNTER))  {
        	SavedEncounter savedEncounter = new SavedEncounter(System.currentTimeMillis(),5);
            performanceMonitoringService.addSavedEncounter(savedEncounter);
            if(log.isDebugEnabled()){
                log.debug("Adding savedEncounter entry : " + savedEncounter.getTimestamp());
            }
       }
       else if (method.getName().equals(SAVE_FORM))  { 
    	   SavedForm savedForm = new SavedForm(System.currentTimeMillis(),9);
            performanceMonitoringService.addSavedForm(savedForm);
            if(log.isDebugEnabled()){
                log.debug("Adding savedForm entry : " + savedForm.getTimestamp());
            }
       }
       else if (method.getName().equals(SAVE_PATIENT))  {
           SavedPatient savedPatient = new SavedPatient(System.currentTimeMillis(),7);
           performanceMonitoringService.addSavedPatient(savedPatient);
           if(log.isDebugEnabled()){
               log.debug("Adding savedPatient entry : " + savedPatient.getTimestamp());
           }
      }
      else if (method.getName().equals(RUN_REPORT))  {
           RanReport ranReport = new RanReport(System.currentTimeMillis(),17);
           performanceMonitoringService.addRanReport(ranReport);
           if(log.isDebugEnabled()){
               log.debug("Adding ranReport entry : " + ranReport.getTimestamp());
           }
      }
      else  if (method.getName().equals(SAVE_CONCEPT))  {
      	SavedConcept savedConcept = new SavedConcept(System.currentTimeMillis(),11);
        performanceMonitoringService.addSavedConcept(savedConcept);
        if(log.isDebugEnabled()){
            log.debug("Adding savedConcept entry : " + savedConcept.getTimestamp());
        }
      }
      else if (method.getName().equals(SAVE_OBSERVATION))  { 
    	  SavedObservation savedObservation = new SavedObservation(System.currentTimeMillis(),13);
    	  performanceMonitoringService.addSavedObservation(savedObservation);
    	  if(log.isDebugEnabled()){
    		  log.debug("Adding savedObservation entry : " + savedObservation.getTimestamp());
    	  }
      }
      else if (method.getName().equals(SAVE_VISIT))  {
    	  SavedVisit savedVisit = new SavedVisit(System.currentTimeMillis(),15);
    	  performanceMonitoringService.addSavedVisit(savedVisit);
    	  if(log.isDebugEnabled()){
    		  log.debug("Adding savedVisit entry : " + savedVisit.getTimestamp());
    	  }
      }
    }
}
