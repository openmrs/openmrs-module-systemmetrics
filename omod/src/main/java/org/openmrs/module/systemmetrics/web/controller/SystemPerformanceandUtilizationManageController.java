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
import org.openmrs.module.systemmetrics.MetricValue;
import org.openmrs.module.systemmetrics.PerMinMetricValue;
import org.openmrs.module.systemmetrics.PerformanceMonitoringUtils;
import org.openmrs.module.systemmetrics.api.PerformanceMonitoringService;
import org.openmrs.module.systemmetrics.api.collectors.LoggedInUsersCountCollectorThread;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * The main controller.
 */
@Controller
public class  SystemPerformanceandUtilizationManageController {
	
	protected final Log log = LogFactory.getLog(getClass());
    protected LoggedInUsersCountCollectorThread loggedInUsersCountCollectorThread;

    @RequestMapping(value = "/module/systemmetrics/manage", method = RequestMethod.GET)
	public void manage(HttpRequest request, ModelMap model) {
        model.addAttribute("user", Context.getAuthenticatedUser());
        loggedInUsersCountCollectorThread = new LoggedInUsersCountCollectorThread();
        Thread loginThread = new Thread(loggedInUsersCountCollectorThread);
        loginThread.start();
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
}
