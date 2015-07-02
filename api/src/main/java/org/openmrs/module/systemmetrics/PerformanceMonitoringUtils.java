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
        currentMetrics.add(usedMem);
        currentMetrics.add(freeMem);
        currentMetrics.add(logins);
        currentMetrics.add(createdEncounters);
        currentMetrics.add(savedEncounter);
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
        savedEncounterEntriesDeletionThread = new SavedEncounterEntriesDeletionThread();
        Thread savedEncountersDeletor = new Thread(savedEncounterEntriesDeletionThread);
        savedEncountersDeletor.start();
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
	
    public static void stopCurrentlyRunningProcesses() {
        usedMemoryCollectorThread.stopUsedMemoryCollector();
        perMinuteUsedMemoryCollectorThread.stopPerMinMemoryCollector();
        usedMemoryDeletionThread.stopUsedMemoryDeletionThread();
        perMinuteUsedMemoryDeletionThread.stopPerminUsedMemoryDeletionThread();
        perMinuteUserLoginsCollectorThread.stopPerMinuteUserLoginsCollectorr();
        formPerHourEntryCollectorThread.stopFormCountCollectorThread();
        encounterPerHourEntryCollectorThread.stopEncounterCountCollectorThread();
        patientPerHourEntryCollectorThread.stopPatientCountCollectorThread();
        savedEncounterEntriesDeletionThread.stopSavedEncounterEntriesDeletionThread();
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
