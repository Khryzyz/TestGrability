package co.torres.christhian.testgrability.appdetail.events;

import java.util.List;

import co.torres.christhian.testgrability.models.AppInfo;

/**
 * Created by Ceindetec02 on 09/11/2016.
 */
public class AppDetailEvent {

    public final static int onLoadSuccess = 0;
    public final static int onLoadError = 1;


    private int eventType;
    private String errorMessage;
    private AppInfo appInfo;

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

    public AppInfo getAppInfo() {
        return appInfo;
    }

    public void setAppInfo(AppInfo appInfo) {
        this.appInfo = appInfo;
    }
}
