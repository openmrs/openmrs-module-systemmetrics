/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.systemmetrics.web.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.systemmetrics.*;
import org.openmrs.module.systemmetrics.api.PerformanceMonitoringService;
import org.openmrs.module.systemmetrics.api.collectors.LoggedInUsersCountCollectorThread;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.ReflectionException;
import javax.servlet.http.HttpServletRequest;

import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;

/**
 * The main controller.
 */
@Controller
public class  SystemPerformanceandUtilizationManageController {
	
	protected final Log log = LogFactory.getLog(getClass());
    protected LoggedInUsersCountCollectorThread loggedInUsersCountCollectorThread;

    @RequestMapping(value = "/module/systemmetrics/manage", method = RequestMethod.GET)
	public void manage(ModelMap model) {
        model.addAttribute("user", Context.getAuthenticatedUser());

        }

    @RequestMapping(value = "/module/systemmetrics/usedMemoryChart", method = RequestMethod.GET)
    public void chart(ModelMap model) {
        model.addAttribute("user", Context.getAuthenticatedUser());
        PerformanceMonitoringService service = PerformanceMonitoringUtils.getService();
        // We get the memory data in the previous minute to display in the graph
        List<MetricValue> valueList = service.getMetricValuesForChart(System.currentTimeMillis()-120000,System.currentTimeMillis());
        String dataToGraph = PerformanceMonitoringUtils.prepareDataToGraph(valueList);
        model.addAttribute("values", dataToGraph);
        List<PerMinMetricValue> perMinValueList = service.getPerMinMetricValuesForChart(System.currentTimeMillis() - 1200000, System.currentTimeMillis());
        String perMinDataToGraph = PerformanceMonitoringUtils.preparePerMinDataToGraph(perMinValueList);
        model.addAttribute("perMinValues", perMinDataToGraph);

    }
	
    @RequestMapping(value = "/module/systemmetrics/usedMemoryPerMin", method = RequestMethod.GET)
    public @ResponseBody String chartMemoryPerMin(ModelMap model) {
        model.addAttribute("user", Context.getAuthenticatedUser());
        PerformanceMonitoringService service = PerformanceMonitoringUtils.getService();
        List<PerMinMetricValue> perMinValueList = service.getPerMinMetricValuesForChart(System.currentTimeMillis() - 1200000, System.currentTimeMillis());
        String perMinDataToGraph = PerformanceMonitoringUtils.preparePerMinDataToGraph(perMinValueList);
        return perMinDataToGraph;

    }
    
    @RequestMapping(value = "/module/systemmetrics/usedMemory", method = RequestMethod.GET)
    public @ResponseBody String chartMemory(ModelMap model) {
        model.addAttribute("user", Context.getAuthenticatedUser());
        PerformanceMonitoringService service = PerformanceMonitoringUtils.getService();
        // We get the memory data in the previous minute to display in the graph
        List<MetricValue> valueList = service.getMetricValuesForChart(System.currentTimeMillis()-120000,System.currentTimeMillis());
        String dataToGraph = PerformanceMonitoringUtils.prepareDataToGraph(valueList);
        return dataToGraph;
    }
    
	@RequestMapping(value = "/module/systemmetrics/usedCPUChart", method = RequestMethod.GET)
	public void cpuChart(ModelMap model) {
        model.addAttribute("user", Context.getAuthenticatedUser());
    }
	
	@RequestMapping(value = "/module/systemmetrics/usedDiskChart", method = RequestMethod.GET)
	public void diskChart(ModelMap model) {
        model.addAttribute("user", Context.getAuthenticatedUser());
    }
	
	@RequestMapping(value = "/module/systemmetrics/systemInfo", method = RequestMethod.GET)
	public void systemInfo(ModelMap model) {
        model.addAttribute("user", Context.getAuthenticatedUser());
    }
	
    @RequestMapping(value = "/module/systemmetrics/enable_tracking", method = RequestMethod.GET)
    public void track(HttpServletRequest request) {
        loggedInUsersCountCollectorThread = new LoggedInUsersCountCollectorThread(request.getSession());
        Thread loginThread = new Thread(loggedInUsersCountCollectorThread);
        loginThread.start();

    }
    
    @RequestMapping(value = "/module/systemmetrics/applicationGlobal", method = RequestMethod.GET)
    public void applicationGlobal(ModelMap model) {
        model.addAttribute("user", Context.getAuthenticatedUser());
    }
    
    @RequestMapping(value = "/module/systemmetrics/appGlobalTable", method = RequestMethod.GET)
    public void appGlobalTable(ModelMap model) {
        model.addAttribute("user", Context.getAuthenticatedUser());
    }

    @RequestMapping(value = "/module/systemmetrics/globalAppData", method = RequestMethod.GET)
    public  @ResponseBody List<String> globalAppData(ModelMap model) {
        model.addAttribute("user", Context.getAuthenticatedUser());
        List<String> appDataList = new ArrayList<String>();
        String dataToGraph = new String();
        PerformanceMonitoringService service = PerformanceMonitoringUtils.getService();
        
        dataToGraph = PerformanceMonitoringUtils.prepareEncountersDataToGraph(service.getEncountersPerHourEntryForChart(System.currentTimeMillis() - 43200000, System.currentTimeMillis()));
        appDataList.add("Encounter");
        appDataList.add(dataToGraph);
        
        dataToGraph = PerformanceMonitoringUtils.prepareFormsDataToGraph(service.getFormsPerHourEntryForChart(System.currentTimeMillis() - 43200000, System.currentTimeMillis()));
        appDataList.add("Form");
        appDataList.add(dataToGraph);
        
        dataToGraph = PerformanceMonitoringUtils.preparePatientsDataToGraph(service.getPatientsPerHourEntryForChart(System.currentTimeMillis() - 43200000, System.currentTimeMillis()));
        appDataList.add("Patient");
        appDataList.add(dataToGraph);
        
        dataToGraph = PerformanceMonitoringUtils.prepareConceptsDataToGraph(service.getConceptsPerHourEntryForChart(System.currentTimeMillis() - 43200000, System.currentTimeMillis()));
        appDataList.add("Concept");
        appDataList.add(dataToGraph);
        
        dataToGraph = PerformanceMonitoringUtils.prepareObservationsDataToGraph(service.getObservationsPerHourEntryForChart(System.currentTimeMillis() - 43200000, System.currentTimeMillis()));
        appDataList.add("Observation");
        appDataList.add(dataToGraph);
        
        dataToGraph = PerformanceMonitoringUtils.prepareVisitsDataToGraph(service.getVisitsPerHourEntryForChart(System.currentTimeMillis() - 43200000, System.currentTimeMillis()));
        appDataList.add("Visit");
        appDataList.add(dataToGraph);
        
        dataToGraph = PerformanceMonitoringUtils.prepareReportsDataToGraph(service.getReportsPerHourEntryForChart(System.currentTimeMillis() - 43200000, System.currentTimeMillis()));
        appDataList.add("Report");
        appDataList.add(dataToGraph);

		return appDataList;
    }
    

    @RequestMapping(value = "/module/systemmetrics/globalAppTableData", method = RequestMethod.GET)
    public  @ResponseBody List<String> globalAppTableData(ModelMap model) {
        model.addAttribute("user", Context.getAuthenticatedUser());
        List<String> appDataList = new ArrayList<String>();
        Long dataToGraph = new Long(0l);
        PerformanceMonitoringService service = PerformanceMonitoringUtils.getService();
        
        dataToGraph = 0l;
        for(EncountersPerHourEntry entry : service.getEncountersPerHourEntryForChart(System.currentTimeMillis() - 43200000, System.currentTimeMillis())){
        	dataToGraph += entry.getEncounterCount();
        }
        appDataList.add("Encounter");
        appDataList.add(dataToGraph.toString());
        
        dataToGraph = 0l;
        for(FormsPerHourEntry entry : service.getFormsPerHourEntryForChart(System.currentTimeMillis() - 43200000, System.currentTimeMillis())){
        	dataToGraph += entry.getFormCount();
        }
        appDataList.add("Form");
        appDataList.add(dataToGraph.toString());

        dataToGraph = 0l;
        for(PatientsPerHourEntry entry : service.getPatientsPerHourEntryForChart(System.currentTimeMillis() - 43200000, System.currentTimeMillis())){
        	dataToGraph += entry.getPatientCount();
        }
        appDataList.add("Patient");
        appDataList.add(dataToGraph.toString());
        
        dataToGraph = 0l;
        for(ConceptsPerHourEntry entry : service.getConceptsPerHourEntryForChart(System.currentTimeMillis() - 43200000, System.currentTimeMillis())){
        	dataToGraph += entry.getConceptCount();
        }
        appDataList.add("Concept");
        appDataList.add(dataToGraph.toString());
        
        dataToGraph = 0l;
        for(ObservationsPerHourEntry entry : service.getObservationsPerHourEntryForChart(System.currentTimeMillis() - 43200000, System.currentTimeMillis())){
        	dataToGraph += entry.getObservationCount();
        }
        appDataList.add("Observation");
        appDataList.add(dataToGraph.toString());
        
        dataToGraph = 0l;
        for(VisitsPerHourEntry entry : service.getVisitsPerHourEntryForChart(System.currentTimeMillis() - 43200000, System.currentTimeMillis())){
        	dataToGraph += entry.getVisitCount();
        }
        appDataList.add("Visit");
        appDataList.add(dataToGraph.toString());
        
        dataToGraph = 0l;
        for(ReportsPerHourEntry entry : service.getReportsPerHourEntryForChart(System.currentTimeMillis() - 43200000, System.currentTimeMillis())){
        	dataToGraph += entry.getReportCount();
        }
        appDataList.add("Report");
        appDataList.add(dataToGraph.toString());
        
		return appDataList;
    }
    
    
    @RequestMapping(value = "/module/systemmetrics/loggedInUsers", method = RequestMethod.GET)
    public void countLogins(ModelMap model) {
        model.addAttribute("user", Context.getAuthenticatedUser());
        PerformanceMonitoringService service = PerformanceMonitoringUtils.getService();
        // We get the login data in the previous 5 minute to display in the graph
        List<LoginValue> valueList = service.getLoginValuesForChart(System.currentTimeMillis() - 300000, System.currentTimeMillis());
        String dataToGraph = PerformanceMonitoringUtils.prepareLoginDataToGraph(valueList);
        model.addAttribute("logInValues", dataToGraph);
        // We get the login data in the previous hour to display in the graph
        List<PerMinLoginValue> hourlyValueList = service.getPerMinLoginValuesForChart(System.currentTimeMillis() - 3600000, System.currentTimeMillis());
        String hourlyDataToGraph = PerformanceMonitoringUtils.prepareHourlyLoginDataToGraph(hourlyValueList);
        model.addAttribute("perMinLogInValues", hourlyDataToGraph);

    }

    /*
    * We get the encounter creation data in the previous 12 hours to display in the graph
    */
    @RequestMapping(value = "/module/systemmetrics/createdEncounters", method = RequestMethod.GET)
    public void countEncounters(ModelMap model) {
        model.addAttribute("user", Context.getAuthenticatedUser());
        PerformanceMonitoringService service = PerformanceMonitoringUtils.getService();
        List<EncountersPerHourEntry> valueList = service.getEncountersPerHourEntryForChart(System.currentTimeMillis() - 43200000, System.currentTimeMillis());
        String dataToGraph = PerformanceMonitoringUtils.prepareEncountersDataToGraph(valueList);
        model.addAttribute("encounterCounts", dataToGraph);

    }

    /*
    * We get the form creation data in the previous 12 hours to display in the graph
    */
    @RequestMapping(value = "/module/systemmetrics/createdForms", method = RequestMethod.GET)
    public void countForms(ModelMap model) {
        model.addAttribute("user", Context.getAuthenticatedUser());
        PerformanceMonitoringService service = PerformanceMonitoringUtils.getService();
        List<FormsPerHourEntry> valueList = service.getFormsPerHourEntryForChart(System.currentTimeMillis() - 43200000, System.currentTimeMillis());
        String dataToGraph = PerformanceMonitoringUtils.prepareFormsDataToGraph(valueList);
        model.addAttribute("formCounts", dataToGraph);

    }

    /*
    * We get the patient creation data in the previous 12 hours to display in the graph
    */
    @RequestMapping(value = "/module/systemmetrics/createdPatients", method = RequestMethod.GET)
    public void countPatients(ModelMap model) {
        model.addAttribute("user", Context.getAuthenticatedUser());
        PerformanceMonitoringService service = PerformanceMonitoringUtils.getService();
        List<PatientsPerHourEntry> valueList = service.getPatientsPerHourEntryForChart(System.currentTimeMillis() - 43200000, System.currentTimeMillis());
        String dataToGraph = PerformanceMonitoringUtils.preparePatientsDataToGraph(valueList);
        model.addAttribute("patientCounts", dataToGraph);

    }
    /*
    * We get the visit creation data in the previous 12 hours to display in the graph
    */
    @RequestMapping(value = "/module/systemmetrics/createdVisits", method = RequestMethod.GET)
    public void countVisits(ModelMap model) {
        model.addAttribute("user", Context.getAuthenticatedUser());
        PerformanceMonitoringService service = PerformanceMonitoringUtils.getService();
        List<VisitsPerHourEntry> valueList = service.getVisitsPerHourEntryForChart(System.currentTimeMillis() - 43200000, System.currentTimeMillis());
        String dataToGraph = PerformanceMonitoringUtils.prepareVisitsDataToGraph(valueList);
        model.addAttribute("visitCounts", dataToGraph);

    }

    /*
    * We get the observation creation data in the previous 12 hours to display in the graph
    */
    @RequestMapping(value = "/module/systemmetrics/createdObservations", method = RequestMethod.GET)
    public void countObservations(ModelMap model) {
        model.addAttribute("user", Context.getAuthenticatedUser());
        PerformanceMonitoringService service = PerformanceMonitoringUtils.getService();
        List<ObservationsPerHourEntry> valueList = service.getObservationsPerHourEntryForChart(System.currentTimeMillis() - 43200000, System.currentTimeMillis());
        String dataToGraph = PerformanceMonitoringUtils.prepareObservationsDataToGraph(valueList);
        model.addAttribute("observationCounts", dataToGraph);

    }
    /*
    * We get the concept creation data in the previous 12 hours to display in the graph
    */
    @RequestMapping(value = "/module/systemmetrics/createdConcepts", method = RequestMethod.GET)
    public void countConcepts(ModelMap model) {
        model.addAttribute("user", Context.getAuthenticatedUser());
        PerformanceMonitoringService service = PerformanceMonitoringUtils.getService();
        List<ConceptsPerHourEntry> valueList = service.getConceptsPerHourEntryForChart(System.currentTimeMillis() - 43200000, System.currentTimeMillis());
        String dataToGraph = PerformanceMonitoringUtils.prepareConceptsDataToGraph(valueList);
        model.addAttribute("conceptCounts", dataToGraph);

    }
    /*
    * We get the report creation data in the previous 12 hours to display in the graph
    */
    @RequestMapping(value = "/module/systemmetrics/ranReports", method = RequestMethod.GET)
    public void countReports(ModelMap model) {
        model.addAttribute("user", Context.getAuthenticatedUser());
        PerformanceMonitoringService service = PerformanceMonitoringUtils.getService();
        List<ReportsPerHourEntry> valueList = service.getReportsPerHourEntryForChart(System.currentTimeMillis() - 43200000, System.currentTimeMillis());
        String dataToGraph = PerformanceMonitoringUtils.prepareReportsDataToGraph(valueList);
        model.addAttribute("reportCounts", dataToGraph);

    }
	 /*
     * CPU Usage of JVM
     */
	@RequestMapping(value = "/module/systemmetrics/cpu_usage_jvm", method = RequestMethod.GET)
	public @ResponseBody double cpuUsageJVM(ModelMap model) throws MalformedObjectNameException, NullPointerException, InstanceNotFoundException, ReflectionException 
	{	
        model.addAttribute("user", Context.getAuthenticatedUser());
        
	    MBeanServer mbs    = ManagementFactory.getPlatformMBeanServer();
	    ObjectName name    = ObjectName.getInstance("java.lang:type=OperatingSystem");
	    AttributeList list = mbs.getAttributes(name, new String[]{ "ProcessCpuLoad" });

	    if (list.isEmpty())     return Double.NaN;

	    Attribute att = (Attribute)list.get(0);
	    Double value  = (Double)att.getValue();

	    if (value.doubleValue() == -1.0)      return Double.NaN;  // usually takes a couple of seconds before we get real values

	    return ((int)(value.doubleValue() * 1000) / 10.0);        // returns a percentage value with 1 decimal point precision
	}
    /*
     * CPU Usage Total
     */
	@RequestMapping(value = "/module/systemmetrics/cpu_usage", method = RequestMethod.GET)
	public @ResponseBody double cpuUsage(ModelMap model) throws MalformedObjectNameException, NullPointerException, InstanceNotFoundException, ReflectionException 
	{	
        model.addAttribute("user", Context.getAuthenticatedUser());
        com.sun.management.OperatingSystemMXBean operatingSystemMXBean = 
		         (com.sun.management.OperatingSystemMXBean)ManagementFactory.getOperatingSystemMXBean();
	    return operatingSystemMXBean.getSystemCpuLoad();        // returns a percentage value with 1 decimal point precision
	}
    /* 
     * Total number of processors or cores available to the JVM 
     */
	@RequestMapping(value = "/module/systemmetrics/processor_avail_jvm", method = RequestMethod.GET)
	public @ResponseBody int processorAvailJVM(ModelMap model)
	{	
        model.addAttribute("user", Context.getAuthenticatedUser());
        return Runtime.getRuntime().availableProcessors();
	}
	/* 
     * Total amount of free memory available to the JVM
     */
	@RequestMapping(value = "/module/systemmetrics/memory_free_jvm", method = RequestMethod.GET)
	public @ResponseBody long memoryFreeJVM(ModelMap model)
	{	
        model.addAttribute("user", Context.getAuthenticatedUser());
        return Runtime.getRuntime().freeMemory();
	}
    /* 
     * Maximum amount of memory the JVM will attempt to use
     */
	@RequestMapping(value = "/module/systemmetrics/memory_avail_jvm", method = RequestMethod.GET)
	public @ResponseBody long memoryAvailJVM(ModelMap model)
	{	
        model.addAttribute("user", Context.getAuthenticatedUser());
        return Runtime.getRuntime().maxMemory();
	}
    /* 
     * Maximum amount of memory the JVM will attempt to use
     */
	@RequestMapping(value = "/module/systemmetrics/memory_total_jvm", method = RequestMethod.GET)
	public @ResponseBody long memoryTotalJVM(ModelMap model)
	{	
        model.addAttribute("user", Context.getAuthenticatedUser());
        return Runtime.getRuntime().totalMemory();
	}
    /* 
     * Maximum amount of memory; RAM Size
     */
	@RequestMapping(value = "/module/systemmetrics/memory_total", method = RequestMethod.GET)
	public @ResponseBody long memoryTotal(ModelMap model)
	{	
        model.addAttribute("user", Context.getAuthenticatedUser());
        com.sun.management.OperatingSystemMXBean operatingSystemMXBean = 
		         (com.sun.management.OperatingSystemMXBean)ManagementFactory.getOperatingSystemMXBean();
        return operatingSystemMXBean.getTotalPhysicalMemorySize();
	}
    /* 
     * Maximum amount of Page memory; Page File Size
     */
	@RequestMapping(value = "/module/systemmetrics/memory_total_page", method = RequestMethod.GET)
	public @ResponseBody long memoryTotalPage(ModelMap model)
	{	
        model.addAttribute("user", Context.getAuthenticatedUser());
        com.sun.management.OperatingSystemMXBean operatingSystemMXBean = 
		         (com.sun.management.OperatingSystemMXBean)ManagementFactory.getOperatingSystemMXBean();
        return operatingSystemMXBean.getTotalSwapSpaceSize();
	}
    /* 
     * Free amount of Physical memory; Free RAM
     */
	@RequestMapping(value = "/module/systemmetrics/memory_free", method = RequestMethod.GET)
	public @ResponseBody long memoryFree(ModelMap model)
	{	
        model.addAttribute("user", Context.getAuthenticatedUser());
        com.sun.management.OperatingSystemMXBean operatingSystemMXBean = 
		         (com.sun.management.OperatingSystemMXBean)ManagementFactory.getOperatingSystemMXBean();
        return operatingSystemMXBean.getFreePhysicalMemorySize();
	}
    /* 
     * Free amount of Page memory; Free Page File Size
     */
	@RequestMapping(value = "/module/systemmetrics/memory_free_page", method = RequestMethod.GET)
	public @ResponseBody long memoryFreePage(ModelMap model)
	{	
        model.addAttribute("user", Context.getAuthenticatedUser());
        com.sun.management.OperatingSystemMXBean operatingSystemMXBean = 
		         (com.sun.management.OperatingSystemMXBean)ManagementFactory.getOperatingSystemMXBean();
        return operatingSystemMXBean.getFreeSwapSpaceSize();
	}
	 /* 
     * Disk Usage
     */
	@RequestMapping(value = "/module/systemmetrics/disk_usage", method = RequestMethod.GET)
	public @ResponseBody List<DiskParam> diskUsage(ModelMap model)
	{	
        model.addAttribute("user", Context.getAuthenticatedUser());
	    /* Get a list of all filesystem roots on this system */
	    java.io.File[] roots = java.io.File.listRoots();
	    java.io.File root ;
	    List<DiskParam> DiskParams = new ArrayList<DiskParam>();
	    /* For each filesystem root, print some info */
	    for (int i = 0; i<roots.length; i++ ) {
	    root = roots[i];
	    DiskParams.add(new DiskParam(root.getAbsolutePath(),root.getTotalSpace(),root.getFreeSpace(), root.getUsableSpace()));
	    }       
	    return DiskParams;
	}
	 /* 
     * Operating System Version
     */
	@RequestMapping(value = "/module/systemmetrics/get_version", method = RequestMethod.GET)
	public @ResponseBody String getVersion(ModelMap model)
	{	
		model.addAttribute("user", Context.getAuthenticatedUser());
		java.lang.management.OperatingSystemMXBean operatingSystemMXBean = 
		         ManagementFactory.getOperatingSystemMXBean();
        return 	operatingSystemMXBean.getVersion();
	}
	 /* 
     * OS Architecture
     */
	@RequestMapping(value = "/module/systemmetrics/get_arch", method = RequestMethod.GET)
	public @ResponseBody  String getArch(ModelMap model)
	{	
		model.addAttribute("user", Context.getAuthenticatedUser());
		java.lang.management.OperatingSystemMXBean operatingSystemMXBean = 
		         ManagementFactory.getOperatingSystemMXBean();
        return 	operatingSystemMXBean.getArch();
	}
	 /* 
     * OS Name
     */
	@RequestMapping(value = "/module/systemmetrics/get_name", method = RequestMethod.GET)
	public @ResponseBody String getName(ModelMap model)
	{	
		model.addAttribute("user", Context.getAuthenticatedUser());
		java.lang.management.OperatingSystemMXBean operatingSystemMXBean = 
		         ManagementFactory.getOperatingSystemMXBean();
        return 	operatingSystemMXBean.getName();
	}
	 /* 
     * JVM Name
     */
	@RequestMapping(value = "/module/systemmetrics/get_name_jvm", method = RequestMethod.GET)
	public @ResponseBody  String getNameJVM(ModelMap model)
	{	
		model.addAttribute("user", Context.getAuthenticatedUser());
		java.lang.management.RuntimeMXBean runtimeMXBean = 
		         ManagementFactory.getRuntimeMXBean();
        return 	runtimeMXBean.getVmName();
	}
	 /* 
     * JVM Version
     */
	@RequestMapping(value = "/module/systemmetrics/get_version_jvm", method = RequestMethod.GET)
	public @ResponseBody String getNameJVMVersion(ModelMap model)
	{	
		model.addAttribute("user", Context.getAuthenticatedUser());
		java.lang.management.RuntimeMXBean runtimeMXBean = 
		         ManagementFactory.getRuntimeMXBean();
        return 	runtimeMXBean.getVmVersion();
	}
	 /* 
     * JVM Vendor Name
     */
	@RequestMapping(value = "/module/systemmetrics/get_vendor_jvm", method = RequestMethod.GET)
	public @ResponseBody String getNameJVMVendor(ModelMap model)
	{	
		model.addAttribute("user", Context.getAuthenticatedUser());
		java.lang.management.RuntimeMXBean runtimeMXBean = 
		         ManagementFactory.getRuntimeMXBean();
        return 	runtimeMXBean.getVmVendor();
	}
	 /* 
     * Module Existence Check For Google Chrome Extension
     */
	@RequestMapping(value = "/module/systemmetrics/isExisted", method = RequestMethod.GET)
	public @ResponseBody boolean isModuleExisted(ModelMap model)
	{	
		model.addAttribute("user", Context.getAuthenticatedUser());
        return true;
	}
}
