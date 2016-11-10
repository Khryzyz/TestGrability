package co.torres.christhian.testgrability.applist.ui;

import java.util.List;

import co.torres.christhian.testgrability.models.AppInfo;

public interface AppListView {


    void loadDataSuccess(List<AppInfo> appList);

    void loadDataError(String errorMessage);

}
