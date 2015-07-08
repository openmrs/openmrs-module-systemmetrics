package org.openmrs.module.systemmetrics;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.systemmetrics.api.PerformanceMonitoringService;
import org.openmrs.module.systemmetrics.api.collectors.*;

import java.util.*;

public class PerformanceMonitoringUtils {

    public static Log log = LogFactory.getLog(PerformanceMonitoringUtils.class);

    public static UsedMemoryCollectorThread usedMemoryCollectorThread ;
    public static PerMinuteUsedMemoryCollectorThread perMinuteUsedMemoryCollectorThread;
    public static UsedMemoryDeletionThread usedMemoryDeletionThread;
    public static PerMinuteUsedMemoryDeletionThread perMinuteUsedMemoryDeletionThread;
    public static PerMinuteUserLoginsCollectorThread perMinuteUserLoginsCollectorThread;
    public static PatientsPerHourEntryCollectorThread patientPerHourEntryCollectorThread;
    public static SavedPatientEntriesDeletionThread savedPatientEntriesDeletionThread;
	public static EncountersPerHourEntryCollectorThread encounterPerHourEntryCollectorThread;
    public static SavedEncounterEntriesDeletionThread savedEncounterEntriesDeletionThread;
	public static FormsPerHourEntryCollectorThread formPerHourEntryCollectorThread;
    public static SavedFormEntriesDeletionThread savedFormEntriesDeletionThread;
	public static ConceptsPerHourEntryCollectorThread conceptPerHourEntryCollectorThread;
    public static SavedConceptEntriesDeletionThread savedConceptEntriesDeletionThread;
	public static ObservationsPerHourEntryCollectorThread observationPerHourEntryCollectorThread;
    public static SavedObservationEntriesDeletionThread savedObservationEntriesDeletionThread;
	public static VisitsPerHourEntryCollectorThread visitPerHourEntryCollectorThread;
    public static SavedVisitEntriesDeletionThread savedVisitEntriesDeletionThread;
	public static ReportsPerHourEntryCollectorThread reportPerHourEntryCollectorThread;
    public static RanReportEntriesDeletionThread ranReportEntriesDeletionThread;
    public static LoggedInUsersDeletionThread loggedInUsersDeletionThread;

    /**
     * Returns the PerformanceMonitoring service from the Context
     *
     * @return PerformanceMonitoring service
     */
    public static PerformanceMonitoringService getService() {
        return Context.getService(PerformanceMonitoringService.class);
    }

    public static void loadCurrentlyAvailableMetircTypes() {
        List<MetricType> currentMetrics = new ArrayList<MetricType>();
        MetricType usedMem = getService().addMetricType(new MetricType(1, "Used Memory", "long"));
        MetricType freeMem = getService().addMetricType(new MetricType(2,"Free Memory", "long"));
        MetricType logins = getService().addMetricType(new MetricType(3,"Logged in Users", "int"));
        MetricType createdEncounters = getService().addMetricType(new MetricType(4,"Encounters Created", "int"));
        MetricType savedEncounter = getService().addMetricType(new MetricType(5,"Saved Encounter", "object"));
        MetricType createdPatients = getService().addMetricType(new MetricType(6,"Patients Created", "int"));
        MetricType savedPatient = getService().addMetricType(new MetricType(7,"Saved Patient", "object"));
        MetricType createdForms = getService().addMetricType(new MetricType(8,"Forms Created", "int"));
        MetricType savedForm = getService().addMetricType(new MetricType(9,"Saved Form", "object"));
        MetricType createdConcepts = getService().addMetricType(new MetricType(10,"Concepts Created", "int"));
        MetricType savedConcept = getService().addMetricType(new MetricType(11,"Saved Concept", "object"));
        MetricType createdObservations = getService().addMetricType(new MetricType(12,"Observations Created", "int"));
        MetricType savedObservation = getService().addMetricType(new MetricType(13,"Saved Observation", "object"));
        MetricType createdVisits = getService().addMetricType(new MetricType(14,"Visits Created", "int"));
        MetricType savedVisit = getService().addMetricType(new MetricType(15,"Saved Visit", "object"));
        MetricType createdReports = getService().addMetricType(new MetricType(16,"Reports Created", "int"));
        MetricType ranReport = getService().addMetricType(new MetricType(17,"Ran Report", "object"));
        
        currentMetrics.add(usedMem);
        currentMetrics.add(freeMem);
        currentMetrics.add(logins);
        currentMetrics.add(createdEncounters);
        currentMetrics.add(savedEncounter);
        currentMetrics.add(createdPatients);
        currentMetrics.add(savedPatient);
        currentMetrics.add(createdForms);
        currentMetrics.add(savedForm);
        currentMetrics.add(createdConcepts);
        currentMetrics.add(savedConcept);
        currentMetrics.add(createdObservations);
        currentMetrics.add(savedObservation);
        currentMetrics.add(createdVisits);
        currentMetrics.add(savedVisit);
        currentMetrics.add(createdReports);
        currentMetrics.add(ranReport);
    }

    public static void startInitialProcesses(){

        loadCurrentlyAvailableMetircTypes();
        usedMemoryCollectorThread = new UsedMemoryCollectorThread();
        Thread collectorThread = new Thread(usedMemoryCollectorThread);
        collectorThread.start();
        perMinuteUsedMemoryCollectorThread = new PerMinuteUsedMemoryCollectorThread();
        Thread perMinCollectorThread = new Thread(perMinuteUsedMemoryCollectorThread);
        perMinCollectorThread.start();
        usedMemoryDeletionThread = new UsedMemoryDeletionThread();
        Thread deletorThread = new Thread(usedMemoryDeletionThread);
        deletorThread.start();
        perMinuteUsedMemoryDeletionThread = new PerMinuteUsedMemoryDeletionThread();
        Thread perMinDeletorThread = new Thread(perMinuteUsedMemoryDeletionThread);
        perMinDeletorThread.start();
        perMinuteUserLoginsCollectorThread = new PerMinuteUserLoginsCollectorThread();
        Thread perMinuteUserLoginsCollector = new Thread(perMinuteUserLoginsCollectorThread);
        perMinuteUserLoginsCollector.start();
        formPerHourEntryCollectorThread = new FormsPerHourEntryCollectorThread();
        Thread formsCountCollector = new Thread(formPerHourEntryCollectorThread);
        formsCountCollector.start();
        encounterPerHourEntryCollectorThread = new EncountersPerHourEntryCollectorThread();
        Thread encountersCountCollector = new Thread(encounterPerHourEntryCollectorThread);
        encountersCountCollector.start();
        patientPerHourEntryCollectorThread = new PatientsPerHourEntryCollectorThread();
        Thread patientCountCollector = new Thread(patientPerHourEntryCollectorThread);
        patientCountCollector.start();
        conceptPerHourEntryCollectorThread = new ConceptsPerHourEntryCollectorThread();
        Thread conceptsCountCollector = new Thread(conceptPerHourEntryCollectorThread);
        conceptsCountCollector.start();
        observationPerHourEntryCollectorThread = new ObservationsPerHourEntryCollectorThread();
        Thread observationsCountCollector = new Thread(observationPerHourEntryCollectorThread);
        observationsCountCollector.start();
        visitPerHourEntryCollectorThread = new VisitsPerHourEntryCollectorThread();
        Thread visitCountCollector = new Thread(visitPerHourEntryCollectorThread);
        visitCountCollector.start();
        reportPerHourEntryCollectorThread = new ReportsPerHourEntryCollectorThread();
        Thread reportCountCollector = new Thread(reportPerHourEntryCollectorThread);
        reportCountCollector.start();
        
        savedEncounterEntriesDeletionThread = new SavedEncounterEntriesDeletionThread();
        Thread savedEncountersDeletor = new Thread(savedEncounterEntriesDeletionThread);
        savedEncountersDeletor.start();
        savedPatientEntriesDeletionThread = new SavedPatientEntriesDeletionThread();
        Thread savedPatientsDeletor = new Thread(savedPatientEntriesDeletionThread);
        savedPatientsDeletor.start();
        savedFormEntriesDeletionThread = new SavedFormEntriesDeletionThread();
        Thread savedFormsDeletor = new Thread(savedFormEntriesDeletionThread);
        savedFormsDeletor.start();
        savedConceptEntriesDeletionThread = new SavedConceptEntriesDeletionThread();
        Thread savedConceptsDeletor = new Thread(savedConceptEntriesDeletionThread);
        savedConceptsDeletor.start();
        savedVisitEntriesDeletionThread = new SavedVisitEntriesDeletionThread();
        Thread savedVisitsDeletor = new Thread(savedVisitEntriesDeletionThread);
        savedVisitsDeletor.start();
        savedObservationEntriesDeletionThread = new SavedObservationEntriesDeletionThread();
        Thread savedObservationsDeletor = new Thread(savedObservationEntriesDeletionThread);
        savedObservationsDeletor.start();
        ranReportEntriesDeletionThread = new RanReportEntriesDeletionThread();
        Thread ranReportsDeletor = new Thread(ranReportEntriesDeletionThread);
        ranReportsDeletor.start();
        
        loggedInUsersDeletionThread = new LoggedInUsersDeletionThread();
        Thread userLoginDeletor = new Thread(loggedInUsersDeletionThread);
        userLoginDeletor.start();

    }

    /**
     * Renders the metric values as [Date,Memory] value pairs and makes a javascript 2d array in order to be
     * parsed to @chart.jsp to draw the memory usage graph
     * @param valueList
     * @return
     */
    public static String prepareDataToGraph(List<MetricValue> valueList) {

        String fullEntry = "";
        for(MetricValue metricValue : valueList){
            // the final parsed array elements would be in format [new Date(1403842448), 636]  etc.
            String oneEntry = "[new Date(" + (metricValue.getTimestamp()) + " ), " + (metricValue.getMetricValue()/1000000) + "],";
            fullEntry = fullEntry + oneEntry;
        }
        return fullEntry;
    }

    /**
     * Renders the metric values as [Date,LoginCount] value pairs and makes a javascript 2d array in order to be
     * parsed to @loggedInUsers.jsp to draw the memory usage graph
     * @param valueList
     * @return
     */
    public static String prepareLoginDataToGraph(List<LoginValue> valueList) {

        String fullEntry = "";
        for(LoginValue loginValue : valueList){
            // the final parsed array elements would be in format [new Date(1403842448), 5]  etc.
            String oneEntry = "[new Date(" + (loginValue.getTimestamp()) + " ), " + loginValue.getLogin_count() + "],";
            fullEntry = fullEntry + oneEntry;
        }
        return fullEntry;
    }

    /**
     * Renders the metric values as [Date,LoginCount] value pairs and makes a javascript 2d array in order to be
     * parsed to @loggedInUsers.jsp to draw the memory usage graph
     * @param valueList - perminuteLogInValue elements list
     * @return
     */
    public static String prepareHourlyLoginDataToGraph(List<PerMinLoginValue> valueList) {

        String fullEntry = "";
        for(PerMinLoginValue loginValue : valueList){
            // the final parsed array elements would be in format [new Date(1403842448), 5]  etc.
            String oneEntry = "[new Date(" + (loginValue.getTimestamp()) + " ), " + loginValue.getLogin_count() + "],";
            fullEntry = fullEntry + oneEntry;
        }
        return fullEntry;
    }

    /**
     * Renders the metric values as [Date,Memory] value pairs and makes a javascript 2d array in order to be
     * parsed to @chart.jsp to draw the memory usage graph
     * @param valueList
     * @return
     */
    public static String prepareFormsDataToGraph(List<FormsPerHourEntry> valueList) {

        String fullEntry = "";
        for(FormsPerHourEntry metricValue : valueList){
            // the final parsed array elements would be in format [new Date(1403842448), 636]  etc.
            String oneEntry = "[new Date(" + (metricValue.getTimestamp()) + " ), " + (metricValue.getFormCount()) + "],";
            fullEntry = fullEntry + oneEntry;
        }
        return fullEntry;
    }
    
    public static String prepareEncountersDataToGraph(List<EncountersPerHourEntry> valueList) {

        String fullEntry = "";
        for(EncountersPerHourEntry metricValue : valueList){
            // the final parsed array elements would be in Format [new Date(1403842448), 636]  etc.
            String oneEntry = "[new Date(" + (metricValue.getTimestamp()) + " ), " + (metricValue.getEncounterCount()) + "],";
            fullEntry = fullEntry + oneEntry;
        }
        return fullEntry;
    }

    public static String preparePatientsDataToGraph(List<PatientsPerHourEntry> valueList) {

        String fullEntry = "";
        for(PatientsPerHourEntry metricValue : valueList){
            // the final parsed array elements would be in Format [new Date(1403842448), 636]  etc.
            String oneEntry = "[new Date(" + (metricValue.getTimestamp()) + " ), " + (metricValue.getPatientCount()) + "],";
            fullEntry = fullEntry + oneEntry;
        }
        return fullEntry;
    }
    
    
    public static String prepareVisitsDataToGraph(List<VisitsPerHourEntry> valueList) {

        String fullEntry = "";
        for(VisitsPerHourEntry metricValue : valueList){
            // the final parsed array elements would be in Format [new Date(1403842448), 636]  etc.
            String oneEntry = "[new Date(" + (metricValue.getTimestamp()) + " ), " + (metricValue.getVisitCount()) + "],";
            fullEntry = fullEntry + oneEntry;
        }
        return fullEntry;
    }

    public static String prepareConceptsDataToGraph(List<ConceptsPerHourEntry> valueList) {

        String fullEntry = "";
        for(ConceptsPerHourEntry metricValue : valueList){
            // the final parsed array elements would be in Format [new Date(1403842448), 636]  etc.
            String oneEntry = "[new Date(" + (metricValue.getTimestamp()) + " ), " + (metricValue.getConceptCount()) + "],";
            fullEntry = fullEntry + oneEntry;
        }
        return fullEntry;
    }
    
    public static String prepareObservationsDataToGraph(List<ObservationsPerHourEntry> valueList) {

        String fullEntry = "";
        for(ObservationsPerHourEntry metricValue : valueList){
            // the final parsed array elements would be in Format [new Date(1403842448), 636]  etc.
            String oneEntry = "[new Date(" + (metricValue.getTimestamp()) + " ), " + (metricValue.getObservationCount()) + "],";
            fullEntry = fullEntry + oneEntry;
        }
        return fullEntry;
    }

    public static String prepareReportsDataToGraph(List<ReportsPerHourEntry> valueList) {

        String fullEntry = "";
        for(ReportsPerHourEntry metricValue : valueList){
            // the final parsed array elements would be in Format [new Date(1403842448), 636]  etc.
            String oneEntry = "[new Date(" + (metricValue.getTimestamp()) + " ), " + (metricValue.getReportCount()) + "],";
            fullEntry = fullEntry + oneEntry;
        }
        return fullEntry;
    }
    
	
    public static void stopCurrentlyRunningProcesses() {
        usedMemoryCollectorThread.stopUsedMemoryCollector();
        perMinuteUsedMemoryCollectorThread.stopPerMinMemoryCollector();
        usedMemoryDeletionThread.stopUsedMemoryDeletionThread();
        perMinuteUsedMemoryDeletionThread.stopPerminUsedMemoryDeletionThread();
        perMinuteUserLoginsCollectorThread.stopPerMinuteUserLoginsCollectorr();
        formPerHourEntryCollectorThread.stopFormCountCollectorThread();
        visitPerHourEntryCollectorThread.stopVisitCountCollectorThread();
        patientPerHourEntryCollectorThread.stopPatientCountCollectorThread();
        conceptPerHourEntryCollectorThread.stopConceptCountCollectorThread();
        observationPerHourEntryCollectorThread.stopObservationCountCollectorThread();
        reportPerHourEntryCollectorThread.stopReportCountCollectorThread();
        visitPerHourEntryCollectorThread.stopVisitCountCollectorThread();
        savedEncounterEntriesDeletionThread.stopSavedEncounterEntriesDeletionThread();
        savedPatientEntriesDeletionThread.stopSavedPatientEntriesDeletionThread();
        savedFormEntriesDeletionThread.stopSavedFormEntriesDeletionThread();
        savedConceptEntriesDeletionThread.stopSavedConceptEntriesDeletionThread();
        savedObservationEntriesDeletionThread.stopSavedObservationEntriesDeletionThread();
        savedVisitEntriesDeletionThread.stopSavedVisitEntriesDeletionThread();
        ranReportEntriesDeletionThread.stopRanReportEntriesDeletionThread();
        loggedInUsersDeletionThread.stopLoggedInUsersDeletionThread();
    }

    public static String preparePerMinDataToGraph(List<PerMinMetricValue> perMinValueList) {
        Collections.sort(perMinValueList);
        String fullEntry = "";
        for(PerMinMetricValue metricValue : perMinValueList){
            // the final parsed array elements would be in format [new Date(1403842448), 636]  etc.
            String oneEntry = "[new Date(" + (metricValue.getTimestamp()) + " ), " + (metricValue.getMetricValue()/1000000) + "],";
            fullEntry = fullEntry + oneEntry;
        }
        return fullEntry;

    }
}
