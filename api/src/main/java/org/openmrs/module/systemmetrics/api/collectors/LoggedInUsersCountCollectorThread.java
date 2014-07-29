package org.openmrs.module.systemmetrics.api.collectors;

import org.openmrs.api.context.Context;
import org.openmrs.module.systemmetrics.LoginValue;
import org.openmrs.module.systemmetrics.api.PerformanceMonitoringService;
import org.openmrs.web.SessionListener;
import org.openmrs.web.user.CurrentUsers;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.ArrayList;
import java.util.List;

public class LoggedInUsersCountCollectorThread implements Runnable {

    private boolean start;
    LoginValue loginValue;
    List<String> currentUsers = new ArrayList<String>();
    HttpSession session;
    PerformanceMonitoringService performanceMonitoringService;

    public LoggedInUsersCountCollectorThread(HttpSession session) {
        this.session=session;
        startLoggedInUsersCountCollectorThread();
    }

    @Override
    public void run() {
        Context.openSession();
        performanceMonitoringService = Context.getService(PerformanceMonitoringService.class);
        while (start){
            currentUsers = CurrentUsers.getCurrentUsernames(session);
            int userCount = currentUsers.size();
            loginValue = new LoginValue(System.currentTimeMillis(),3,userCount);
            performanceMonitoringService.addLoginValue(loginValue);
            try {
                Thread.sleep(30000);
            } catch (InterruptedException e) {
                stopLoggedInUsersCountCollectorThread();
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


}
