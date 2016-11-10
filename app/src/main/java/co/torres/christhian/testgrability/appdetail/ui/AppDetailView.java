package co.torres.christhian.testgrability.appdetail.ui;

import co.torres.christhian.testgrability.models.AppInfo;

/**
 * Created by Ceindetec02 on 09/11/2016.
 */

public interface AppDetailView {


    void loadDataSuccess(AppInfo appInfo);

    void loadDataError(String errorMessage);

}
