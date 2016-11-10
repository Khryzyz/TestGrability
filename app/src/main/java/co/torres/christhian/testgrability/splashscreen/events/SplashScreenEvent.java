package co.torres.christhian.testgrability.splashscreen.events;

public class SplashScreenEvent {
    public final static int onUpdateDataSuccess = 0;
    public final static int onUpdateDataError = 1;
    public final static int onInternetNoConnected = 2;
    public final static int onDataDeprecated = 3;
    public final static int onDataUpdated = 4;
    public final static int onDataError = 5;


    private int eventType;
    private String errorMessage;

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
}
