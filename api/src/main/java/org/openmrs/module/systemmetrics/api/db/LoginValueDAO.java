package org.openmrs.module.systemmetrics.api.db;


import org.openmrs.module.systemmetrics.LoginValue;
import org.openmrs.module.systemmetrics.MetricValue;

import java.util.List;

public interface LoginValueDAO {

    public int getMetricId();

    public String getLoginValue();

    public String getMetricTimestamp();

    public LoginValue addLoginValue(LoginValue loginValue) ;

    public void removeLoginValue(LoginValue loginValue);

    public List<LoginValue> getLoginValuesForChart(long startTimestamp, long endTimestamp);

    public void removeLoginValuesWithinTime(long startTimestamp, long endTimestamp);

}
