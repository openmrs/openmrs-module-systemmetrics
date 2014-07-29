package org.openmrs.module.systemmetrics.api.db;


import org.openmrs.module.systemmetrics.LoginValue;
import org.openmrs.module.systemmetrics.PerMinLoginValue;

import java.util.List;

public interface PerMinLoginValueDAO {

    public int getMetricId();

    public String getPerMinLoginValue();

    public String getMetricTimestamp();

    public PerMinLoginValue addPerMinLoginValue(PerMinLoginValue loginValue);

    public void removeLoginValue(PerMinLoginValue loginValue);

    public List<PerMinLoginValue> getPerMinLoginValuesForChart(long startTimestamp, long endTimestamp);

    public void removePerMinLoginValuesWithinTime(long startTimestamp, long endTimestamp);

}
