package org.openmrs.module.systemmetrics.api.collectors;

import org.openmrs.api.context.Context;
import org.openmrs.module.systemmetrics.LoginValue;
import org.openmrs.module.systemmetrics.api.PerformanceMonitoringService;
import org.openmrs.web.SessionListener;
import org.openmrs.web.user.CurrentUsers;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.ArrayList;
import java.util.List;

public class LoggedInUsersCountCollectorThread implements Runnable, HttpSessionListener {

    private boolean start;
    LoginValue loginValue;
    List<String> currentUsers = new ArrayList<String>();

    PerformanceMonitoringService performanceMonitoringService;

    public LoggedInUsersCountCollectorThread() {
        startLoggedInUsersCountCollectorThread();
    }

    @Override
    public void run() {
        Context.openSession();
        performanceMonitoringService = Context.getService(PerformanceMonitoringService.class);
        while (start){
            int userCount = currentUsers.size();
            loginValue = new LoginValue(System.currentTimeMillis(),3,userCount);
            performanceMonitoringService.addLoginValue(loginValue);
            try {
                System.out.println("Users count - sleeping now " + userCount);
                Thread.sleep(30000);
            } catch (InterruptedException e) {
                /* ignore*/
            }
        }

    }

    private boolean isStarted(){
        return start;
    }

    public void startLoggedInUsersCountCollectorThread(){
        start = true;
    }

    public void stopLoggedInUsersCountCollectorThread(){
        start = false;
    }

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        currentUsers = CurrentUsers.getCurrentUsernames(httpSessionEvent.getSession());
        System.out.println("User logged in " + currentUsers.size());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        System.out.println("User logged out " + currentUsers.size());
       }
}
