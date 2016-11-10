package co.torres.christhian.testgrability.applist.events;

import java.util.List;

import co.torres.christhian.testgrability.models.AppInfo;

/**
 * Created by Ceindetec02 on 09/11/2016.
 */
public class AppListEvent {

    public final static int onLoadSuccess = 0;
    public final static int onLoadError = 1;


    private int eventType;
    private String errorMessage;
    private List<AppInfo> appInfoList;

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public List<AppInfo> getAppInfoList() {
        return appInfoList;
    }

    public void setAppInfoList(List<AppInfo> appInfoList) {
        this.appInfoList = appInfoList;
    }
}
