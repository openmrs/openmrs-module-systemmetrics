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
import org.openmrs.module.systemmetrics.PerformanceMonitoringUtils;
import org.openmrs.module.systemmetrics.MetricType;
import org.openmrs.module.systemmetrics.api.PerformanceMonitoringService;
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

	@RequestMapping(value = "/module/systemmetrics/manage", method = RequestMethod.GET)
	public void manage(ModelMap model) {
        model.addAttribute("user", Context.getAuthenticatedUser());
        }

    @RequestMapping(value = "/module/systemmetrics/chart", method = RequestMethod.GET)
    public void chart(ModelMap model) {
        model.addAttribute("user", Context.getAuthenticatedUser());
        PerformanceMonitoringService service = PerformanceMonitoringUtils.getService();
        // todo: currently we fetch data from table by using timestamp based logic. this needs to be changed to a contant count
        // todo: for the 1.0 release
        List<MetricValue> valueList = service.getMetricValuesForChart(System.currentTimeMillis()-30000,System.currentTimeMillis());
        String dataToGraph = PerformanceMonitoringUtils.prepareDataToGraph(valueList);
        model.addAttribute("values", dataToGraph);

    }
}
