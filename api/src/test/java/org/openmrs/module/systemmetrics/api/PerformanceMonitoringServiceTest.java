package org.openmrs.module.systemmetrics.api;



import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openmrs.module.systemmetrics.*;
import org.openmrs.test.BaseContextSensitiveTest;

import java.util.List;

public class PerformanceMonitoringServiceTest extends BaseContextSensitiveTest {

    protected static final String INITIAL_DATA_XML = "org/openmrs/api/include/PerformanceMonitoringServiceTest-initialData.xml";
    protected PerformanceMonitoringService service;

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Before
    public void setUp() throws Exception {
         service = PerformanceMonitoringUtils.getService();
         executeDataSet(INITIAL_DATA_XML);
    }



    @Test
    public void testAddMetricType() throws Exception {
        System.out.println("Test method");

        MetricType metricType = new MetricType(20,"Test Metric","int");
        service.addMetricType(metricType);

        // get and assert metric type
        MetricType newMetricType = null;
        List<MetricType> list = service.getAllMetricTypes();
        for(MetricType type: list ){
            if(type.getMetricId() == 20){
                newMetricType = type;
            }
        }

        Assert.assertNotNull("MetricType should be added", newMetricType);
        Assert.assertEquals("Metric name should be set","Test Metric",metricType.getMetricName());
        Assert.assertEquals("Metric type should be set","int",metricType.getMetric_type());

    }

    @org.junit.Test
    public void testRemoveMetricType() throws Exception {
        MetricType metricType = new MetricType(20,"Test Metric 2","int");
        service.addMetricType(metricType);

        service.removeMetricType(metricType);
        // get and assert metric type doesn't exist
        MetricType newMetricType = null;
        List<MetricType> list = service.getAllMetricTypes();
        for(MetricType type: list ){
            if(type.getMetricName().equals("Test Metric 2")){
                newMetricType = type;
            }
        }
        Assert.assertNull("Metric type should not be in the db", newMetricType);

    }

    @org.junit.Test
    public void testGetAllMetricTypes() throws Exception {
        MetricType metricType = new MetricType(20,"Test Metric 1","int");
        service.addMetricType(metricType);
        MetricType metricType2 = new MetricType(21,"Test Metric 2","long");
        service.addMetricType(metricType);

        // get and compare the metric tyes list
        List<MetricType> list = service.getAllMetricTypes();
        Assert.assertEquals("Metric type list size is two", 2, list.size() );
    }



    @org.junit.Test
    public void testRemoveMetricVale() throws Exception {
        MetricValue metricValue = new MetricValue(22,System.currentTimeMillis(),600000);
        service.addMetricValue(metricValue);

        service.removeMetricVale(metricValue);
        Query query =  sessionFactory.getCurrentSession().createQuery("from MetricValue where metric_id = 22");
        List<MetricValue> valueList = query.list();
        Assert.assertEquals("Metric value list is empty", 0, valueList.size() );
    }


    @org.junit.Test
    public void testAddPerMinMetricValue() throws Exception {
        PerMinMetricValue perMinMetricValue =
                new PerMinMetricValue(23,System.currentTimeMillis(),500000);
        service.addPerMinMetricValue(perMinMetricValue);

        Query query =  sessionFactory.getCurrentSession().createQuery("from PerMinMetricValue where metric_id = 23");
        List<MetricValue> valueList = query.list();
        Assert.assertEquals("PerMinMetricValue exists in db", 1, valueList.size() );

    }


    @org.junit.Test
    public void testAddLoginValue() throws Exception {
        LoginValue loginValue =
                new LoginValue(System.currentTimeMillis(),24,5);
        service.addLoginValue(loginValue);

        Query query =  sessionFactory.getCurrentSession().createQuery("from LoginValue where metric_id = 24");
        List<MetricValue> valueList = query.list();
        Assert.assertEquals("LoginValue exists in db", 1, valueList.size() );

    }

    @org.junit.Test
    public void testRemoveLoginValue() throws Exception {
        LoginValue loginValue =
                new LoginValue(System.currentTimeMillis(),25,5);
        service.addLoginValue(loginValue);

        service.removeLoginValue(loginValue);
        Query query =  sessionFactory.getCurrentSession().createQuery("from LoginValue where metric_id = 25");
        List<MetricValue> valueList = query.list();
        Assert.assertEquals("LoginValue removed from db", 0, valueList.size() );

    }


    @org.junit.Test
    public void testAddFormsPerHourEntry() throws Exception {

    }

    @org.junit.Test
    public void testAddSavedEncounter() throws Exception {
       SavedEncounter savedEncounter =
               new SavedEncounter(System.currentTimeMillis(),26);
        service.addSavedEncounter(savedEncounter);

        Query query =  sessionFactory.getCurrentSession().createQuery("from SavedEncounter where metric_id = 26");
        List<MetricValue> valueList = query.list();
        Assert.assertEquals("SavedEncounter added to db", 1, valueList.size() );

    }

    @org.junit.Test
    public void testRemoveSavedEncounter() throws Exception {

    }
}
