package co.torres.christhian.testgrability.categorialist.events;

import android.database.Cursor;

import java.util.List;

import co.torres.christhian.testgrability.models.CategoryApp;

public class CategoriaListEvent {
    public final static int onLoadSuccess = 0;
    public final static int onLoadError = 1;


    private int eventType;
    private String errorMessage;
    private List<CategoryApp> categoryAppList;

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

    public List<CategoryApp> getCategoryAppList() {
        return categoryAppList;
    }

    public void setCategoryAppList(List<CategoryApp> categoryAppList) {
        this.categoryAppList = categoryAppList;
    }
}
